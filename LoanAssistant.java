package loanassistant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Objects;

public class LoanAssistant
{
    Font font=new Font("Arial",Font.PLAIN,28);
    JFrame jFrame;
    JLabel lblloanbal,lblintrate,lblnopay,lblmonpay,lblloananal;
    JTextField tfloanbal,tfintrate,tfnopay,tfmonpay;
    JButton cross,cross1,btncompmonpay,btnnewloan,btnexit,btnnopay;
    JTextArea txtloananal;
    double loan,minimumPayment,interest,cointerest,payment,months,multiplier,finalpay,totpay,intpaid;
    int payment1;

    private LoanAssistant() {
        jFrame=new JFrame("Loan Assistant");
        jFrame.setBounds(50,50,1000,500);
        jFrame.setFont(new Font("ARIAL",Font.PLAIN,28));

        //LEFT PART
        lblloanbal=new JLabel("Loan Balance");
        lblloanbal.setBounds(0,10,300,50);
        lblloanbal.setFont(new Font("ARIAL", Font.PLAIN, 28));
        lblintrate=new JLabel("Interest Rate");
        lblintrate.setBounds(0,80,300,50);
        lblintrate.setFont(font);
        lblnopay=new JLabel("Number of Payments");
        lblnopay.setBounds(0,150,300,50);
        lblnopay.setFont(font);
        lblmonpay=new JLabel("Monthly Payment");
        lblmonpay.setBounds(0,220,300,50);
        lblmonpay.setFont(font);

        tfloanbal=new JTextField();
        tfloanbal.setBounds(270,10,200,50);
        tfloanbal.setFont(font);
        tfloanbal.setBackground(Color.white);
        tfintrate=new JTextField();
        tfintrate.setBounds(270,80,200,50);
        tfintrate.setFont(font);
        tfintrate.setBackground(Color.white);
        tfnopay=new JTextField();
        tfnopay.setBounds(270,150,200,50);
        tfnopay.setFont(font);
        tfmonpay=new JTextField();
        tfmonpay.setBounds(270,220,200,50);
        tfmonpay.setFont(font);
        tfmonpay.setBackground(new Color(255, 255, 125));
        tfmonpay.setEditable(false);


        cross=new JButton("x");
        cross.setBounds(480,150,70,50);
        cross.setFont(new Font("Arial",Font.BOLD,28));
        cross.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfnopay.setBackground(new Color(255,255,125));
                tfmonpay.setBackground(Color.white);
                btncompmonpay.setVisible(false);
                tfnopay.setEditable(false);
                tfmonpay.setEditable(true);
                cross.setVisible(false);
                cross1.setVisible(true);
                btnnopay.setVisible(true);
            }
        });

        cross1=new JButton("x");
        cross1.setVisible(false);
        cross1.setBounds(480,220,70,50);
        cross1.setFont(new Font("Arial",Font.BOLD,28));
        cross1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfnopay.setEditable(true);
                cross.setBounds(480,150,70,50);
                tfnopay.setBackground(Color.WHITE);
                tfmonpay.setEditable(false);
                tfmonpay.setBackground(new Color(255,255,125));
                btnnopay.setVisible(false);
                btncompmonpay.setVisible(true);
                cross1.setVisible(false);
                cross.setVisible(true);

            }
        });


        btncompmonpay=new JButton("Compute Monthly Payment");
        btncompmonpay.setBounds(100,290,350,50);
        btncompmonpay.setFont(new Font("Arial",Font.BOLD,18));
        btncompmonpay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btncompmonpay.setEnabled(false);
                btnnewloan.setEnabled(true);
                btnnopay.setEnabled(false);
                computemonthlypayment();

            }
        });

        btnnopay=new JButton("Compute Number of Payments");
        btnnopay.setBounds(100,290,350,50);
        btnnopay.setFont(new Font("Arial",Font.BOLD,18));
        btnnopay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnnewloan.setEnabled(true);
                btnnopay.setEnabled(false);
                btncompmonpay.setEnabled(false);
                computenoofpayment();
            }
        });


        btnnewloan=new JButton("New Loan Analysis");
        btnnewloan.setBounds(140,360,250,50);
        btnnewloan.setFont(new Font("Arial",Font.BOLD,18));
        btnnewloan.setEnabled(false);
        btnnewloan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btncompmonpay.setEnabled(true);
                btnnewloan.setEnabled(false);
                tfloanbal.setText("");
                tfintrate.setText("");
                tfnopay.setText("");
                tfmonpay.setText("");
                btnnopay.setEnabled(true);
                txtloananal.setText("");
            }
        });

        //RIGHT PART
        lblloananal=new JLabel("Loan Analysis");
        lblloananal.setBounds(570,10,300,50);
        lblloananal.setFont(font);

        txtloananal=new JTextArea("",1,1);
        txtloananal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtloananal.setEditable(false);
        txtloananal.setBounds(570,80,400,310);
        txtloananal.setFont(new Font("Arial",Font.PLAIN,18));



        btnexit=new JButton("Exit");
        btnexit.setBounds(700,400,120,50);
        btnexit.setFont(new Font("Arial",Font.BOLD,28));
        btnexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });




        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.add(lblloanbal);
        jFrame.add(lblintrate);
        jFrame.add(lblnopay);
        jFrame.add(lblmonpay);
        jFrame.add(tfloanbal);
        jFrame.add(tfintrate);
        jFrame.add(tfnopay);
        jFrame.add(tfmonpay);
        jFrame.add(cross);
        jFrame.add(btncompmonpay);
        jFrame.add(btnnewloan);
        jFrame.add(lblloananal);
        jFrame.add(txtloananal);
        jFrame.add(btnexit);
        jFrame.add(btnnopay);
        jFrame.add(cross1);

    }

    private void computemonthlypayment()
    {
        if (Objects.equals(tfloanbal.getText(), "") && Objects.equals(tfintrate.getText(), "")
                && Objects.equals(tfnopay.getText(), ""))
        {
            JOptionPane.showMessageDialog(jFrame,"All Fields are Empty!");
            btncompmonpay.setEnabled(true);
            btnnewloan.setEnabled(false);
            return;
        }
        if(validateDecimalNumber(tfloanbal))
        {
            loan= Double.parseDouble(tfloanbal.getText());
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Loan Balance entry.\nPlease correct.", "Number of Payments Input Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            btncompmonpay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfintrate))
        {
            interest= Double.parseDouble(tfintrate.getText());
            cointerest=interest/12/100;
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Interest Rate entry.\nPlease correct.","Interest Input Error", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            btncompmonpay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfnopay))
        {
            payment=(int) Double.parseDouble(tfnopay.getText());
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Number of Payments entry.\nPlease correct.", "Number of Payments Input Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            btncompmonpay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfloanbal)&& validateDecimalNumber(tfintrate)&&validateDecimalNumber(tfnopay) )
        {
            if(interest==0)
            {
                months=loan/payment;
            }
            else
            {
                multiplier=Math.pow(1+cointerest,payment);
                months=loan*cointerest*multiplier/(multiplier-1);
            }
            double finalloan=loan;
            payment1= (int) (payment-1);
            for (int i = 1; i <=payment1-1; i++) {
                finalloan+=finalloan*cointerest-months;
            }
            finalpay=finalloan;
            if(finalpay>payment1)
            {
                finalpay+=finalloan*cointerest-months;
                payment++;
            }

            tfmonpay.setText(String.format("%.2f",Double.parseDouble(String.valueOf(months))));
            totpay=finalpay+payment1*months;
            intpaid=totpay-loan;
            loananalysis();

        }

    }
    private boolean validateDecimalNumber(JTextField tf)
    {
// checks to see if text field contains
// valid decimal number with only digits and a single decimal point
        String s = tf.getText().trim();
        boolean hasDecimal = false;
        boolean valid = true;
        if (s.length() == 0)
        {
            valid = false;
        }
        else
        {
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9')
                {
                    continue;
                }
                else if (c == '.' && !hasDecimal) {
                    hasDecimal = true;
                }
                else
                {
// invalid character found
                    valid = false;
                }
            }
        }
        tf.setText(s);
        if (!valid)
        {
            tf.requestFocus();
        }
        return (valid);
    }
    private void computenoofpayment()
    {
        if (Objects.equals(tfloanbal.getText(), "") && Objects.equals(tfintrate.getText(), "")
                && Objects.equals(tfmonpay.getText(), ""))
        {
            JOptionPane.showMessageDialog(jFrame,"All Fields are Empty!");
            btnnopay.setEnabled(true);
            btnnewloan.setEnabled(false);
            return;
        }
        if(validateDecimalNumber(tfloanbal))
        {
            loan= Double.parseDouble(tfloanbal.getText());
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Loan Balance entry.\nPlease correct.", "Number of Payments Input Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            btnnopay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfintrate))
        {
            interest=Double.parseDouble(tfintrate.getText());
            cointerest=interest/12/100;
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Interest Rate entry.\nPlease correct.", "Number of Payments Input Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            btnnopay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfmonpay))
        {
            months=Double.parseDouble(tfmonpay.getText());
        }
        else
        {
            JOptionPane.showConfirmDialog(jFrame, "Invalid or empty Monthly Payment entry.\nPlease correct.", "Number of Payments Input Error",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
            btnnopay.setEnabled(true);
            btnnewloan.setEnabled(false);
        }
        if(validateDecimalNumber(tfloanbal)&& validateDecimalNumber(tfintrate)&& validateDecimalNumber(tfmonpay))
        {
            minimumPayment=loan*cointerest+1;
            if(minimumPayment>months)
            {
                if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $" +new DecimalFormat("0.00").format((int)(minimumPayment)) + "\n" + "Do youwant to use the minimum payment?", "Input Error", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    months=(int) minimumPayment;
                }

            }
            if(interest==0)
            {
                payment=loan/months;
            }
            else
            {
                payment=Math.log((months/cointerest)/(months/cointerest-loan))/Math.log(1+cointerest);
            }
            double finalloan=loan;
            for (int i = 1; i <=payment-1; i++) {
                finalloan+=finalloan*cointerest-months;
            }
            finalpay=finalloan;
            if(finalpay>payment)
            {
                finalpay+=finalloan*cointerest-months;
                payment++;
            }
            payment1= (int) (payment-1);
            totpay=finalpay+payment1*months;
            intpaid=totpay-loan;
            tfnopay.setText(String.valueOf((int) payment));
            tfmonpay.setText("");
            tfmonpay.setText(String.valueOf(months));
            loananalysis();
        }


    }
    private void loananalysis()
    {
        txtloananal.setText("Loan Balance: $ "+loan+"\nInterest Rate: "+String.format("%.2f",
                Double.parseDouble(String.valueOf(interest)))+"%"+"\n\n"+payment1+" Payments of: $ "
                +String.format("%.2f",Double.parseDouble((String.valueOf(months))))+"\nFinal Payment of: $ "
                +String.format("%.2f",Double.parseDouble((String.valueOf(finalpay))))+"\nTotal Payments: $ "
                +String.format("%.2f",Double.parseDouble((String.valueOf(totpay))))+"\nInterest Paid: $ "
                +String.format("%.2f",Double.parseDouble((String.valueOf(intpaid)))));
    }
    public static void main(String[] args) {
        new LoanAssistant();
    }

}


