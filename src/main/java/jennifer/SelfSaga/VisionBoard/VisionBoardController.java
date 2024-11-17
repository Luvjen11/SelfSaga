package jennifer.SelfSaga.VisionBoard;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/selfsaga/users/{username}/visionboard")
public class VisionBoardController {

    @Autowired
    private VisionBoardService visionBoardService;

    @PostMapping
    public ResponseEntity<VisionBoard> uploadimage(@RequestParam String username, @RequestParam MultipartFile file)
            throws IOException, SQLException, SQLException {
        Blob imageBlob = new SerialBlob(file.getBytes()); // Convert MultipartFile to Blob
        VisionBoard visionBoard = visionBoardService.createImage(username, imageBlob);
        return ResponseEntity.ok(visionBoard);
    }

    @GetMapping
    public ResponseEntity<List<VisionBoard>> getAllImages(@RequestParam String username) {
        return ResponseEntity.ok(visionBoardService.viewAll(username));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteImage(@PathVariable Long id, @RequestParam String username) {
        visionBoardService.deleteImage(id, username);
        return ResponseEntity.ok("Image was deleted successfully.");
    }
}
