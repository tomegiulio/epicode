package com.w6d5.w6d5.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.w6d5.w6d5.model.Device;
import com.w6d5.w6d5.service.DeviceService;

@RestController
@RequestMapping("/api")
public class DeviceController {
	@Autowired
	DeviceService ds;
	
	@GetMapping(path = "/device/{id}")
	public ResponseEntity<Device> findById(@PathVariable(required = true) int id) {
	Optional<Device> find = ds.getById(id);	

		if (find.isPresent()) {
			return new ResponseEntity<>(find.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(path = "/allDevice")
	public ResponseEntity<List<Device>> findAll() {
		List<Device> findAll = ds.getall();

		if (findAll.size()>0) {
			return new ResponseEntity<>(findAll, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

	}
	@PostMapping(path = "admin/postDevice")
	public ResponseEntity<Device> save(@RequestBody Device device) {
		Device save = ds.save(device);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@PutMapping(path = "admin/device/{id}")
	public ResponseEntity<Device> update(@PathVariable int id, @RequestBody Device device) throws Exception {
		Device save = ds.update(id, device);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PutMapping(path = "admin/repairDevice/{id}")
	public ResponseEntity<Device> repair(@PathVariable int id) throws Exception {
		Device save = ds.repair(id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}
	@PutMapping(path = "admin/destroyDevice/{id}")
	public ResponseEntity<Device> destroy(@PathVariable int id) throws Exception {
		Device save = ds.repair(id);
		return new ResponseEntity<>(save, HttpStatus.OK);

	}

	@DeleteMapping(path = "admin/device/{id}")
	public ResponseEntity<String> delete(@PathVariable int id) {
		ds.delete(id);
		return new ResponseEntity<>("Element deleted", HttpStatus.OK);
	}
}
