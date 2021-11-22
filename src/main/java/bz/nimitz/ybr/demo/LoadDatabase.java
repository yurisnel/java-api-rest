package bz.nimitz.ybr.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import bz.nimitz.ybr.demo.model.Serv;
import bz.nimitz.ybr.demo.repository.ServRepository;

@Component
public class LoadDatabase implements CommandLineRunner {

  @Autowired
  private ServRepository repository;
  private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);
  
 
  @Override
  public void run(String... args) throws Exception {   
  
  }
}
