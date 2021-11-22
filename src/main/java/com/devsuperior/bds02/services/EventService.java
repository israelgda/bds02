package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	@Transactional
	public EventDTO update(Long id, EventDTO eventDTO) {
		try {
			
			Event newEvent = eventRepository.getOne(id);
			newEvent.setName(eventDTO.getName());
			newEvent.setDate(eventDTO.getDate());
			newEvent.setUrl(eventDTO.getUrl());
			newEvent.setCity(new City(eventDTO.getCityId(), null));
			
			newEvent = eventRepository.save(newEvent);
			return new EventDTO(newEvent);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
}
