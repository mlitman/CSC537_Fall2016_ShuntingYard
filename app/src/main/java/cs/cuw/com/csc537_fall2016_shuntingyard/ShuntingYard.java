package cs.cuw.com.csc537_fall2016_shuntingyard;

import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by awesomefat on 11/16/16.
 */

public class ShuntingYard
{
    private String inputString;
    private String[] inputs;
    private VisualStack inputQ;
    private VisualStack opStack;
    private VisualStack outputStack;
    private TextView outputTV;

    public ShuntingYard(String inputString)
    {
        this.inputString = inputString;
        this.inputQ = new VisualStack((LinearLayout)Core.mainActivity.findViewById(R.id.inputQLayout));
        this.opStack = new VisualStack((LinearLayout)Core.mainActivity.findViewById(R.id.opQLayout));
        this.outputStack = new VisualStack((LinearLayout)Core.mainActivity.findViewById(R.id.outputQLayout));
        this.outputTV = (TextView)Core.mainActivity.findViewById(R.id.outputTV);
        this.fillInputQ();
    }

    public void nextStep()
    {
        //do we have more work to do before we actually do the math?
        if(this.inputQ.peek() != null)
        {
            Node temp = this.inputQ.pop();
            if(this.isOperator(temp.getValue()))
            {
                //do operator stuff
                if(this.opStack.peek() == null)
                {
                    this.opStack.push(temp);
                }
                else
                {
                    int topPrec = this.getPrecedence(this.opStack.peek().getValue());
                    int newPrec = this.getPrecedence(temp.getValue());

                    while(topPrec >= newPrec)
                    {
                        //pop and try again
                        this.outputStack.push(this.opStack.pop());

                        if(this.opStack.peek() == null)
                        {
                            break;
                        }
                        else
                        {
                            topPrec = this.getPrecedence(this.opStack.peek().getValue());
                        }
                    }

                    //we can finally add the new op
                    this.opStack.push(temp);
                }

            }
            else
            {
                //do value stuff
                this.outputStack.push(temp);
            }
        }
        else if(this.opStack.peek() != null)
        {

            //clear the opStack
            while(opStack.peek() != null)
            {
                this.outputStack.push(this.opStack.pop());
            }
        }
        else
        {
            //time to do the math
            while(this.outputStack.getCount() > 1)
            {
                doMath();
            }
            this.outputTV.setText(this.outputStack.pop().getValue());
        }
    }

    private void doMath()
    {
        String op = this.outputStack.pop().getValue();
        if(this.isOperator(this.outputStack.peek().getValue()))
        {
            this.doMath();
        }
        int op2 = Integer.parseInt(this.outputStack.pop().getValue());

        if(this.isOperator(this.outputStack.peek().getValue()))
        {
            this.doMath();
        }
        int op1 = Integer.parseInt(this.outputStack.pop().getValue());

        if(op.equals("+"))
        {
            this.outputStack.push(new Node("" + (op2 + op1)));
        }
        else if(op.equals("-"))
        {
            this.outputStack.push(new Node("" + (op1 - op2)));
        }
        else if(op.equals("*"))
        {
            this.outputStack.push(new Node("" + (op2 * op1)));
        }
        else if(op.equals("/"))
        {
            this.outputStack.push(new Node("" + (op1 / op2)));
        }
    }

    private int getPrecedence(String s)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("+", 2);
        map.put("-", 2);
        map.put("*", 3);
        map.put("/", 3);
        map.put("^", 4);
        if(map.get(s) != null)
        {
            return map.get(s).intValue();
        }
        else
        {
            return -1;
        }
    }

    private boolean isOperator(String s)
    {
        String ops = "+-*/()^";
        return ops.indexOf(s) != -1;
    }

    private void fillInputQ()
    {
        StringTokenizer st = new StringTokenizer(this.inputString, "+-*/^()", true);
        this.inputs = new String[st.countTokens()];
        int pos = 0;
        while(st.hasMoreTokens())
        {
            this.inputs[pos] = st.nextToken().trim();
            pos++;
        }
        //inputs is filled with all of my tokens with spaces removed

        //add to inputQ
        for(int i = this.inputs.length-1; i >= 0; i--)
        {
            if(this.inputs[i].length() > 0)
            {
                this.inputQ.push(new Node(this.inputs[i]));
            }
        }
    }
}
