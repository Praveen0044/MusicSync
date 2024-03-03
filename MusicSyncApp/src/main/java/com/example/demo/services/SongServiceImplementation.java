package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceImplementation implements SongService{
	@Autowired
	SongRepository songrepo;

	@Override
	public String addSong(Song song) {
		songrepo.save(song);
		return "Song is added ";
	}

	@Override
	public boolean songexist(String name) {
		if((songrepo.findByName(name)==null)){
			return false;
			
		}else {
			
			return true;
		}
		
	}

	@Override
	public List<Song> fetchAllSong() {
		List<Song> listsong = songrepo.findAll();
		return listsong;
	}

	@Override
	public void updateSong(Song song) {
		songrepo.save(song);
		// TODO Auto-generated method stub
		
	}

}
