package com.bluet.bring.getnewsrest;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluet.bring.getnewsrest.blogbuilder.Profile;
import com.bluet.bring.getnewsrest.blogbuilder.ProfileAlreadyExistException;
import com.bluet.bring.getnewsrest.blogbuilder.ProfileNotFoundException;
import com.bluet.bring.getnewsrest.blogbuilder.ProfileService;
import com.bluet.bring.getnewsrest.blogbuilder.repo.IProfileRepo;
import com.bluet.bring.getnewsrest.utils.Utils;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/blogbuilder")
public class BlogBuilderController {

	@Autowired
	ProfileService srv;
	
	/**
	 * Return info about profiles data, like username, and other profile information
	 * Profile are information about the user blog owner 
	 * @param	Profile unique Id generate by the profile generator package
	 */
    @GetMapping(path = "/profile/{id}/{lang}", produces = "application/json")
    public ResponseEntity<?> getProfile(@PathVariable String id, @PathVariable String lang) {
        Optional<Profile> res;
		try {
			res = srv.getProfile(id);
			if (res.isEmpty()){
				throw new ProfileNotFoundException(lang);
			}
		} catch(ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }

	/**
	 * Create Profile information like username
	 * Profile are information about the user blog owner 
	 * @param	Profile unique Id generate by the profile generator package
	 * @lang	ISO language id for return messages translated
	 */
    @PostMapping(path = "profile/", produces= "application/json", consumes= "application/json")
    public ResponseEntity<?> createProfile(@RequestBody Profile profile, @PathVariable Optional<String> lang) {
        String isoLang;
        if (!lang.isPresent()) {
            isoLang = "en";
        } else {
        	isoLang = lang.get();
        }

    	Optional<Profile> res;
		try {
			res = srv.save(profile);
			if(res.isEmpty()) {
				throw new ProfileNotFoundException(lang.get());
			}
		} catch( ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
        return ResponseEntity.ok(res);
    }
    


}
