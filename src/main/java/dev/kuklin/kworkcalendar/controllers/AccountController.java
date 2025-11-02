package dev.kuklin.kworkcalendar.controllers;

import dev.kuklin.kworkcalendar.entities.AssistantGoogleOAuth;
import dev.kuklin.kworkcalendar.services.google.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/google")
@RequiredArgsConstructor
public class AccountController {
    private final TokenService tokenService;

    @GetMapping("/status/{telegram_id}")
    public AssistantGoogleOAuth status(@PathVariable Long telegram_id) {
        return tokenService.findByTelegramIdOrNull(telegram_id);
    }

    @PostMapping("/calendar/{telegram_id}")
    public void setDefaultCalendar(@PathVariable Long telegram_id, @RequestParam String calendarId) {
        tokenService.setDefaultCalendarOrNull(telegram_id, calendarId);
    }

    @DeleteMapping("/disconnect/{telegram_id}")
    public void disconnect(@PathVariable Long telegram_id) {
        tokenService.revokeAndDelete(telegram_id);
    }
}
