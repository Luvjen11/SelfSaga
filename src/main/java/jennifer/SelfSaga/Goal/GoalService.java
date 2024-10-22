package jennifer.SelfSaga.Goal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jennifer.SelfSaga.User.UserRepository;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserRepository userRepository;

    

}
