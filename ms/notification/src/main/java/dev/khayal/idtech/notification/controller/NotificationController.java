package dev.khayal.idtech.notification.controller;

import dev.khayal.idtech.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notifications")
@RequiredArgsConstructor
public class NotificationController {
	private final NotificationService notificationService;

}
