package com.crio.starter.repositoryService;

import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemeRepositoryServiceImpl implements MemeRepositoryService{

    @Autowired
    MemeRepository memeRepository;


    @Override
    public List<Meme> getLatest100Memes(){
        List<Meme> allMemes = memeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return allMemes.stream().limit(100).collect(Collectors.toList());
    }

}
