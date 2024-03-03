package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Song;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongsController {

	@Autowired
	SongService sServ;
	
	@Autowired
	UsersService uServ;

	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Song song) {
		if(sServ.songexist(song.getName())== false) {
			sServ.addSong(song);
			return "addsongsuccess";
		}
		else {
			return "songaddfail";
		}

	}
	@GetMapping("/map-viewsong")
	public String adminViewSong(Model model) {
		List<Song> listsongValue = sServ.fetchAllSong();
		model.addAttribute("songs", listsongValue);

		return "viewSong";
	}
	@GetMapping("/map-viewCustomerSong")
	public String customerViewSong(Model model,HttpSession session) {
			List<Song> listsongValue = sServ.fetchAllSong();
			model.addAttribute("songs", listsongValue);
			String username = uServ.usernameFind((String)session.getAttribute("email"));
			model.addAttribute("username", username);

			return "viewCustomerSong";
	}
}
