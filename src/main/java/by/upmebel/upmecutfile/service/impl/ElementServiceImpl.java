package by.upmebel.upmecutfile.service.impl;

import by.upmebel.upmecutfile.domain.Element;
import by.upmebel.upmecutfile.domain.ElementSide;
import by.upmebel.upmecutfile.exception.ElementNotFoundException;
import by.upmebel.upmecutfile.mapper.ElementMapper;
import by.upmebel.upmecutfile.repository.ElementRepository;
import by.upmebel.upmecutfile.repository.ElementSideRepository;
import by.upmebel.upmecutfile.repository.HoleRepository;
import by.upmebel.upmecutfile.service.ElementService;
import by.upmebel.upmecutfile.util.ElementSideUtils;
import by.upmebel.upmecutfile.web.dto.request.element.CreateElementRequest;
import by.upmebel.upmecutfile.web.dto.request.element.UpdateElementRequest;
import by.upmebel.upmecutfile.web.dto.response.element.ElementInfo;
import by.upmebel.upmecutfile.web.dto.response.element.ElementsPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElementServiceImpl implements ElementService {

    private final ElementRepository elementRepository;
    private final ElementSideRepository elementSideRepository;
    private final HoleRepository holeRepository;
    private final ElementMapper elementMapper;

    @Override
    @Transactional(readOnly = true)
    public ElementInfo getElementById(Long elementId) {
        Element element = elementRepository.findByIdWithSides(elementId)
                .orElseThrow(() -> new ElementNotFoundException(elementId));
        elementSideRepository.findAllByElementIdWithHoles(elementId);

        return elementMapper.map(element);
    }

    @Override
    @Transactional(readOnly = true)
    public ElementsPageResponse getElements(Pageable pageable) {
        Page<Element> elementsPage = elementRepository.findAllWithSides(pageable);
        List<Long> sideIds = elementsPage.getContent().stream()
                .map(Element::getId)
                .toList();
        elementSideRepository.findAllByIdsWithHoles(sideIds);

        return elementMapper.map(elementsPage);
    }

    @Override
    @Transactional
    public ElementInfo createElement(CreateElementRequest request) {
        Element element = elementMapper.map(request);

        List<ElementSide> sides = element.getSides();
        sides.forEach(side -> side.setElement(element));
        completeMissingSides(sides);

        element.setCreatedAt(new Date());
        Element savedElement = elementRepository.save(element);
        return elementMapper.map(savedElement);
    }

    @Override
    @Transactional
    public ElementInfo updateElement(Long elementId, UpdateElementRequest request) {
        Element oldElement = elementRepository.findByIdWithSides(elementId)
                .orElseThrow(() -> new ElementNotFoundException(elementId));
        Queue<Long> updatedSidesIds = oldElement.getSides().stream()
                .map(ElementSide::getId)
                .sorted(Long::compareTo)
                .collect(Collectors.toCollection(ArrayDeque::new));

        Element updatedElement = elementMapper.map(request);
        List<ElementSide> updatedSides = updatedElement.getSides();
        completeMissingSides(updatedSides);

        for (ElementSide side : updatedSides) {
            side.setId(updatedSidesIds.poll());
            side.setElement(updatedElement);
        }

        updatedElement.setId(elementId);
        updatedElement.setCreatedAt(oldElement.getCreatedAt());

        elementRepository.save(updatedElement);
        holeRepository.deleteByElementId(elementId);
        return elementMapper.map(updatedElement);
    }

    @Override
    public void deleteById(Long elementId) {
        elementRepository.deleteById(elementId);
    }

    private void completeMissingSides(List<ElementSide> sides){
        if (sides.size() == 2) {
            ElementSide missingSide = ElementSideUtils.getMissingSide(sides.getFirst(), sides.getLast());
            sides.add(missingSide);
        }
        if (sides.size() == 3) {
            List<ElementSide> clonedSides = ElementSideUtils.cloneSides(sides);
            sides.addAll(clonedSides);
        }
    }

}
