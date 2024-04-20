package com.crio.starter.repositoryService;

import java.util.List;
import com.crio.starter.data.Meme;

public interface MemeRepositoryService {
    List<Meme> getLatest100Memes();
}
