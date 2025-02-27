package com.flyingpig.cloudmusic.music.service;

import com.flyingpig.cloudmusic.music.dataobject.dto.MusicSearchResult;
import com.flyingpig.cloudmusic.music.dataobject.es.MusicDoc;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface MusicSearchService {
    void musicEsInit() throws IOException;

    List<MusicDoc> searchMusicByEs(String keyword) throws IOException;

    List<MusicSearchResult> searchMusic(String keyword);
}
