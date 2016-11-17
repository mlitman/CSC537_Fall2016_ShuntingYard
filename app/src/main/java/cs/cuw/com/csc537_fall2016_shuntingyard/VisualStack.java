package cs.cuw.com.csc537_fall2016_shuntingyard;

import android.widget.LinearLayout;

/**
 * Created by awesomefat on 11/16/16.
 */

public class VisualStack
{
    private LinearLayout theView;
    private Node top;
    private int count;

    public VisualStack(LinearLayout theView)
    {
        this.theView = theView;
        this.theView.removeAllViews();
        this.top = null;
        this.count = 0;
    }

    public void push(Node n)
    {
        n.setNextNode(this.top);
        this.top = n;
        this.theView.addView(this.top.getTheView(), 0);
        this.count++;
    }

    public Node pop()
    {
        Node nodeToRemove = this.top;
        if(this.top != null)
        {
            this.top = this.top.getNextNode();
            this.theView.removeViewAt(0);
            this.count--;
        }
        return nodeToRemove;
    }

    public Node peek()
    {
        return this.top;
    }

    public int getCount()
    {
        return count;
    }
}
