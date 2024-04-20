package com.crio.starter.service;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.DuplicateMemeException;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemeResponseDTO;
import com.crio.starter.repository.MemeRepository;
import com.crio.starter.repositoryService.MemeRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MemeServiceImpl implements MemeService {

  @Autowired
  private MemeRepository memeRepository;

  @Autowired
  private MemeRepositoryService memeRepositoryService;

  @Override
  public MemeResponseDTO addMeme(MemeDto memeDTO){

    // Check if the same meme already exists
    if (memeRepository.existsByNameAndUrlAndCaption(memeDTO.getName(), memeDTO.getUrl(), memeDTO.getCaption())) {
      throw new DuplicateMemeException();
  }
  
    Meme meme=new Meme();
    meme.setName(memeDTO.getName());
    meme.setCaption(memeDTO.getCaption());
    meme.setUrl(memeDTO.getUrl());

    Meme savedMeme= memeRepository.save(meme);
    
    MemeResponseDTO responseDTO=new MemeResponseDTO();
    responseDTO.setId(savedMeme.getId());
    return responseDTO;
   
  }

  @Override
  public List<Meme> getLatest100Memes(){
     return memeRepositoryService.getLatest100Memes();
  }

  @Override
  public Meme getMemeById(String id){
    return memeRepository.findById(id).orElse(null);
  }


}
