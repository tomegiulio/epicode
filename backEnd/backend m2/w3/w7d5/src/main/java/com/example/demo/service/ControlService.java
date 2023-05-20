package com.example.demo.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Control;
import com.example.demo.model.SmokeSystem;
import com.example.demo.repository.ControlRepository;

@Service
public class ControlService {
@Autowired
private ControlRepository cr;

public void save(Control c) {
	cr.save(c);
}

public Set<SmokeSystem> getAllSys(int id) throws Exception {
	Set<SmokeSystem> sonde= new HashSet<>();
	Optional<Control> controlResult=cr.findById(id);
	if(controlResult.isPresent()) {
			Control control=controlResult.get();
			for(SmokeSystem x:control.getSonde()) {
				sonde.add(x);
			}
			return sonde;
	}else {
		throw new Exception("Get smoke system failed");
	}
}
public Set<SmokeSystem> getAllert(int id) throws Exception {
	Set<SmokeSystem> sonde= new HashSet<>();
	Optional<Control> controlResult=cr.findById(id);
	if(controlResult.isPresent()) {
			Control control=controlResult.get();
			for(SmokeSystem x:control.getSonde()) {
				if(x.getSmoke()>0) {
				sonde.add(x);
			}}
			return sonde;
	}else {
		throw new Exception("Get smoke system failed");
	}
}
public Set<String> Allarm(int id) throws Exception {
	Set<String> sonde= new HashSet<>();
	Optional<Control> controlResult=cr.findById(id);
	if(controlResult.isPresent()) {
			Control control=controlResult.get();
			for(SmokeSystem x:control.getSonde()) {
				if(x.getSmoke()>0) {
					String y= "allarme numero: "+x.getId()+" triggerato a latitudine "+x.getLatudine()+" e longitudine "+x.getLogitudine()+" con un livello di fumo "+x.getSmoke()+" URL (http://host/alarm?="+x.getId()+"&lat="+x.getLatudine()+"&lon="+x.getLogitudine()+"&smokelevel="+x.getSmoke();
					System.out.println(" URL (http://localhost:8080/api/control/1/allarm)");
					sonde.add(y);
				
			}}
			return sonde;
	}else {
		throw new Exception("Get smoke system failed");
	}
}

public Optional<Control> getById(int id) {
	return cr.findById(id);
	
}

}
