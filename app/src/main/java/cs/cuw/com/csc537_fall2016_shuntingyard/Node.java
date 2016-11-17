package cs.cuw.com.csc537_fall2016_shuntingyard;

import android.widget.TextView;

/**
 * Created by awesomefat on 11/16/16.
 */

public class Node
{
    private String value;
    private Node nextNode;
    private TextView theView;

    public Node(String value)
    {
        this.value = value;
        this.nextNode = null;
        this.theView = new TextView(Core.appContext);
        this.theView.setText(this.value);
    }

    public Node getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }

    public String getValue()
    {
        return value;
    }

    public TextView getTheView()
    {
        return theView;
    }
}
