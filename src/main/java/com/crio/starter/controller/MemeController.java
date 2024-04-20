package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import java.util.Collections;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.exceptions.DuplicateMemeException;
import com.crio.starter.exchange.MemeDto;
import com.crio.starter.exchange.MemeResponseDTO;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeController {

  @Autowired
  private MemeService memeService;

  @PostMapping("/")
  public ResponseEntity<?> addMeme(@RequestBody MemeDto memeDto){

    if (memeDto.getName() == null || memeDto.getUrl() == null || memeDto.getCaption() == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Meme data cannot be empty");
  }

    try {
        MemeResponseDTO memeResponseDTO=memeService.addMeme(memeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(memeResponseDTO);
    }catch(DuplicateMemeException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Duplicate Meme");
    }

  }

  @GetMapping("/")
  public ResponseEntity<?>  getLatest100Memes() {
    
    List<Meme> memes=memeService.getLatest100Memes();
    if(memes.isEmpty()){
      
      return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
    }
    return ResponseEntity.status(HttpStatus.OK).body(memes);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getMemeById(@PathVariable String id){

    Meme meme = memeService.getMemeById(id);
    if(meme == null){
      return new ResponseEntity<>("Meme Not Found",HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(meme,HttpStatus.OK);

  }

}
