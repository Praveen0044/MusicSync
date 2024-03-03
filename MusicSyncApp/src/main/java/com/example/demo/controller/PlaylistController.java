package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;

@Controller
public class PlaylistController {

	@Autowired
	PlaylistService pServ;
	
	@Autowired
	SongService sServ;
	@GetMapping("/map-createplaylist")
	public String admincreatePlayList(Model model) {
		//fetching the song using song service
		List<Song> songlist=sServ.fetchAllSong();
		//Adding the songs in the model
		model.addAttribute("songlist", songlist);
		//sending createplaylist
		return "createPlaylist";
	}
	@GetMapping("/map-createCustomerPlaylist")
	public String createCustomerPlayList(Model model) {
		//fetching the song using song service
		List<Song> songlist=sServ.fetchAllSong();
		//Adding the songs in the model
		model.addAttribute("songlist", songlist);
		//sending createplaylist
		return "createCustomerPlaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		//adding the playlist
		
		pServ.addPlaylist(playlist);
		//update the song table
		List<Song> songlist = playlist.getSong();
		//traverse list
		for(Song song: songlist) {
			song.getPlaylist().add(playlist);
			sServ.updateSong(song);
		}
		return "playlistsuccess";	
	}
	@PostMapping("/addCustomerPlaylist")
	public String addCustomerPlaylist(@ModelAttribute Playlist playlist) {
		//adding the playlist
		
		pServ.addPlaylist(playlist);
		//update the song table
		List<Song> songlist = playlist.getSong();
		//traverse list
		for(Song song: songlist) {
			song.getPlaylist().add(playlist);
			sServ.updateSong(song);
		}
		return "playlistsuccess";	
	}
	@GetMapping("/map-viewplaylist")
	public String viewadminPlaylist(Model model) {
		List<Playlist> plist = pServ.fetchPlaylist();
		model.addAttribute("plist", plist);
		return "viewPlaylist";
	}
	@GetMapping("/map-viewCustomerplaylist")
	public String viewCustomerPlaylist(Model model) {
		List<Playlist> plist = pServ.fetchPlaylist();
		model.addAttribute("plist", plist);
		return "viewCustomerPlaylist";
	}

	@GetMapping("/map-PremiumPlaylist")
	public String viewPremiumAddedPlaylist(Model model) {
		List<Playlist> plist = pServ.fetchPlaylist();
		model.addAttribute("plist", plist);
		return "viewCustomerPlaylist";
	}
}
