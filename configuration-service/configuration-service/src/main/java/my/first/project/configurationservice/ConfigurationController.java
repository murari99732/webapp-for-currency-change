package my.first.project.configurationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigurationController {
	
	@Autowired
	ConfigurationFile configuration;

	@GetMapping("/config")
	public GitConfiguration retriveConfigurationFromGit()
	{
		return new GitConfiguration( configuration.getMaximum(),configuration.getMinimum());
		
	}
	
}
