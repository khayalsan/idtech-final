package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.entity.NotificationEntity;
import dev.khayal.idtech.notification.event.Event;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import dev.khayal.idtech.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public void send(NotificationEntity notification) {
		// TODO: implement
		notificationRepository.save(notification);
	}
}
