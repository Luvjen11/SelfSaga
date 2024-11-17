package jennifer.SelfSaga.VisionBoard;

import java.sql.Blob;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jennifer.SelfSaga.User.User;
import jennifer.SelfSaga.User.UserRepository;

@Service
public class VisionBoardService {

    @Autowired
    private VisionBoardRepository visionBoardRepository;

    @Autowired
    private UserRepository userRepository;

    public VisionBoard createImage(String username, Blob image) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not found with username: " + username));

        VisionBoard visionBoard = new VisionBoard();
        visionBoard.setImage(image);
        visionBoard.setUser(user);
        return visionBoardRepository.save(visionBoard);
    }

    // return the whole vision board
    public List<VisionBoard> viewAll(String username) {
        return visionBoardRepository.findAllByUsername(username);
    }

    public void deleteImage(Long id, String username) {

        VisionBoard visionBoard = visionBoardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Imagg by id:" + id + "cannot be found"));
        
        if (!visionBoard.getUser().getUsername().equals(username)) {
            throw new SecurityException("You are not authorized to delete this image.");
        }
        visionBoardRepository.delete(visionBoard);
    }
}
