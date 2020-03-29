package ch.uzh.ifi.seal.soprafs20.controller;

import ch.uzh.ifi.seal.soprafs20.entity.User;
import ch.uzh.ifi.seal.soprafs20.rest.dto.UserAuthDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.UserGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.UserPostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.mapper.DTOMapper;
import ch.uzh.ifi.seal.soprafs20.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * User Controller
 * This class is responsible for handling all REST request that are related to the user.
 * The controller will receive the request and delegate the execution to the UserService and finally return the result.
 */
@RestController
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserGetDTO> getAllUsers() {
        // fetch all users in the internal representation
        List<User> users = userService.getUsers();
        List<UserGetDTO> userGetDTOs = new ArrayList<>();

        // convert each user to the API representation
        for (User user : users) {
            userGetDTOs.add(DTOMapper.INSTANCE.convertEntityToUserGetDTO(user));
        }
        return userGetDTOs;
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserGetDTO registerUser(@RequestBody UserPostDTO userPostDTO) {
        // convert API user to internal representation
        User userInput = DTOMapper.INSTANCE.convertUserPostDTOtoEntity(userPostDTO);

        // create user
        User createdUser = userService.createUser(userInput);

        // convert internal representation of user back to API
        return DTOMapper.INSTANCE.convertEntityToUserGetDTO(createdUser);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserGetDTO getUserByUserName(@RequestHeader("X-Auth-Token") String token, @PathVariable Long userId){
        UserGetDTO userGetDTO = new UserGetDTO();
        return  userGetDTO;

    }

    @GetMapping("/user/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public UserAuthDTO login(@RequestBody UserPostDTO userPostDTO){
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        return userAuthDTO;
    }

    @PutMapping("/logout/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void logout(@RequestHeader("X-Auth-Token") String token,@PathVariable Long userId){
        return;
    }

    @PutMapping("/user/{userId}/edit")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserGetDTO updateUser(@RequestHeader("X-Auth-Token") String token,@RequestBody User user, @PathVariable Long userId){
        UserGetDTO userGetDTO = new UserGetDTO();
        return userGetDTO;
    }

    @PutMapping("/user/{userId}/password")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void updatePassword(@RequestHeader("X-Auth-Token") String token,@RequestBody UserPostDTO userPostDTO){
        return;
    }

    @PutMapping("/user/{userId}/invitation")
    public List<Long> invitation(@RequestHeader("X-Auth-Token") String token,@PathVariable Long userId){

        return new ArrayList<Long>();

    }


}
