package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.crio.starter.data.Meme;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.repository.MemeRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class GreetingsServiceTest {

  @Mock
  private MemeRepository greetingsRepository;

  @InjectMocks
  private GreetingsService greetingsService;

  @Test
  void getMessage() {
    Meme greetingsEntity = getGreeting("001", "Welcome");
    Mockito.doReturn(greetingsEntity)
        .when(greetingsRepository).findByExtId("001");
    MemeDto responseDto = greetingsService.getMessage("001");

    MemeDto expected = new MemeDto("Welcome");
    assertEquals(expected, responseDto);

  }

  private Meme getGreeting(String id, String message) {
    Meme greetingsEntity = new Meme();
    greetingsEntity.setExtId(id);
    greetingsEntity.setMessage(message);
    return greetingsEntity;
  }
}