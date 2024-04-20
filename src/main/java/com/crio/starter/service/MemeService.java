package com.crio.starter.service;

import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.DuplicateMemeException;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemeResponseDTO;
import java.util.List;

public interface MemeService {

    MemeResponseDTO addMeme(MemeDto memeDTO) throws DuplicateMemeException;
    List<Meme> getLatest100Memes();
    Meme getMemeById(String id);
}


