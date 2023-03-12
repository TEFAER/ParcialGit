package api.cinestar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.CorsRegistry;

import api.cinestar.model.Cine;
import api.cinestar.model.CineList;
import api.cinestar.model.CinePeliculaList;
import api.cinestar.model.CineTarifaList;
import api.cinestar.model.Pelicula;
import api.cinestar.model.PeliculaList;
import api.cinestar.repository.ICineList;
import api.cinestar.repository.ICinePeliculaList;
import api.cinestar.repository.ICineTarifaList;
import api.cinestar.repository.IPelicula;
import api.cinestar.repository.Icine;
import api.cinestar.repository.IPeliculaList;

@Configuration
@CrossOrigin
@RestController
@RequestMapping

public class CineStarController extends WebMvcAutoConfiguration{
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**");
		
	}
	@Autowired
	private IPeliculaList iPeliculaList;
	
	@Autowired
	private IPelicula iPelicula;
	
	@Autowired
	private Icine iCine;
	
	@Autowired
	private ICineList iCineList;
	
	@Autowired
	private ICineTarifaList ICineTarifaList;
	
	@Autowired
	private ICinePeliculaList ICinePeliculaList;
	

	
	
	@GetMapping("/peliculas/{idx}")
	public List<PeliculaList> getPeliculas(@PathVariable("idx")String idx){
		if (idx != null && (idx.equals("cartelera")|| idx.equals("estrenos")))
		return (List<PeliculaList>) iPeliculaList.getPeliculas(idx.equals("cartelera")? 1 : 2);
		return null;
	}
	
	
	@GetMapping("/pelicula/{id}")
	public Pelicula getPelicula(@PathVariable("id") int id ){
		return (Pelicula) iPelicula.getPelicula(id);
	}
	
	@GetMapping("/cines")
	public List<CineList>  getCines(){
		return (List<CineList>) iCineList.getCines();
	}
	
	@GetMapping("/cine/{id}")
	public Cine getCine(@PathVariable("id") int id ){
		return (Cine) iCine.getCine(id);
	}
	
	@GetMapping("/cine/{id}/tarifas")
	public List<CineTarifaList> getCineTarifaList(@PathVariable("id") int id){
		return (List<CineTarifaList>) ICineTarifaList.getCineTarifaList(id);
		
	}
	@GetMapping("/cine/{id}/peliculas")
	public List<CinePeliculaList> getCinePeliculaLists(@PathVariable("id") int id){
		return (List<CinePeliculaList>) ICinePeliculaList.getCinePeliculaList(id);
	}

}
