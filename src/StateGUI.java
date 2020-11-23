public class StateGUI {

    StateGUIType type;

    public StateGUI(StateGUIType type){
        this.type = type;
    }

    public StateGUIType getStateType(){
        return type;
    }

    public void setStateType(StateGUIType newState){
        type = newState;
    }
}
