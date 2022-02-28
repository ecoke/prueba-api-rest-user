package cl.empresa.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cl.empresa.api.mapper.Mapper;
import cl.empresa.api.mapper.imp.MapperImp;

@Configuration
public class ConfigBean{

	@Bean
	public ModelMapper modelMapper() {
		 return new ModelMapper();
	}

	@Bean
	public Mapper mapper() {
		 return new MapperImp(modelMapper());
	}
 	
	
 
 	
	
}
