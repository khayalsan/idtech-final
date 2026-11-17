package dev.khayal.idtech.notification.service;

import dev.khayal.idtech.notification.entity.NotificationEntity;
import dev.khayal.idtech.notification.event.Event;
import dev.khayal.idtech.notification.mapper.NotificationMapper;
import dev.khayal.idtech.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
	private final NotificationRepository notificationRepository;

	public void send(NotificationEntity notification) {
		log.info("Sending notification: {}", notification);
		// TODO: implement
		log.info("Sent notification: {}", notification);
		notificationRepository.save(notification);
	}
}
