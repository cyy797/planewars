import com.cxy.main.GameFrame;
import com.cxy.util.DataStore;

public class GameStart {
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        DataStore.put("gameFrame",gameFrame);
        gameFrame.init();
    }
}
