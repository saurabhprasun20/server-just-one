package ch.uzh.ifi.seal.soprafs20.service;


import ch.uzh.ifi.seal.soprafs20.entity.Lobby;
import ch.uzh.ifi.seal.soprafs20.exceptions.LobbyException;
import ch.uzh.ifi.seal.soprafs20.repository.LobbyRepository;
import ch.uzh.ifi.seal.soprafs20.rest.dto.LobbyGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.mapper.DTOMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Lobby Service
 * This class is the "worker" and responsible for all functionality related to the lobby
 * (e.g., it creates, modifies). The result will be passed back to the caller.
 */

@Service
@Transactional
public class LobbyService {


    private final Logger log = LoggerFactory.getLogger(LobbyService.class);

    private final LobbyRepository lobbyRepository;

    @Autowired
    public LobbyService(@Qualifier("lobbyRepository") LobbyRepository lobbyRepository) {
        this.lobbyRepository = lobbyRepository;
    }

    public Lobby createLobby(Lobby newLobby){

        log.info("****Here I am ***");

        checkIfLobbyExist(newLobby);
        lobbyRepository.save(newLobby);

        return newLobby;

    }

    public void saveOrUpdate(Lobby updateLobby){
        lobbyRepository.save(updateLobby);
    }

    public List<LobbyGetDTO> getAllLobbies(){
        List<Lobby> lobbyList = this.lobbyRepository.findAll();
        List<LobbyGetDTO> lobbyGetDTOList = new ArrayList<>();
        for(Lobby tempLobby:lobbyList){
            LobbyGetDTO lobbyGetDTO = DTOMapper.INSTANCE.convertEntityToLobbyGetDTO(tempLobby);
            lobbyGetDTOList.add(lobbyGetDTO);
        }
        return lobbyGetDTOList;
    }

    public void removePlayerFromLobby(long id, long userId){
        Lobby lobby = getLobby(id);
        String baseErrorMessage = "This player id is invalid. Please provide proper id";
        if(lobby.getPlayerIds().contains(userId)){
            lobby.getPlayerIds().remove(userId);
        }
        else{
            throw new LobbyException(baseErrorMessage);
        }

        saveOrUpdate(lobby);
    }

    public void addPlayerToLobby(long id, long userId){
        Lobby lobby = getLobby(id);

        String baseErrorMessage = "The lobby cannot have more than 7 player. Please join different lobby";
        if(lobby.getPlayerIds().size()>=7){
            throw new LobbyException(String.format(baseErrorMessage,id));
        }
        lobby.getPlayerIds().add(userId);
        saveOrUpdate(lobby);
    }

    public Lobby getLobby(Long id){

        Lobby lobby = this.lobbyRepository.findById(id).get();

        String baseErrorMessage = "The lobby %d doesn't exist. Please check the lobby which you are joining";
        if(null == lobby){
            throw new LobbyException(baseErrorMessage);
        }
        return lobby;
    }

    public void checkIfLobbyExist(Lobby lobbyToBeCreated) {
        Lobby newLobby = lobbyRepository.findByName(lobbyToBeCreated.getName());

        String baseErrorMessage = "The provided %s is not unique. Therefore, the lobby could not be created!";
        if (null != newLobby) {
            throw new LobbyException(String.format(baseErrorMessage, "lobby name"));
        }
    }
}
