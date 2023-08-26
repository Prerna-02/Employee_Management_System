import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


class MainFrame extends JFrame
{
Container c;
JButton btnAdd,btnView,btnUpdate,btnDelete;

MainFrame()
{
c=getContentPane();
c.setBackground(Color.CYAN);
c.setLayout(null);




btnAdd=new JButton("Add");
btnView=new JButton("View");
btnUpdate=new JButton("Update");
btnDelete=new JButton("Delete");

Font f=new Font("Cambria",Font.BOLD,20);
btnAdd.setFont(f);
btnView.setFont(f);
btnUpdate.setFont(f);
btnDelete.setFont(f);

// btnAdd.setBackground(Color.RED);
// btnAdd.setForeground(Color.BLACK);

btnAdd.setBounds(100,140,250,65);
btnView.setBounds(450,140,250,65);
btnUpdate.setBounds(100,280,250,65);
btnDelete.setBounds(450,280,250,65);

c.add(btnAdd);
c.add(btnView);
c.add(btnUpdate);
c.add(btnDelete);



ActionListener a1=(ae) ->
{
AddFrame a=new AddFrame();
dispose();
};
btnAdd.addActionListener(a1);

ActionListener a2=(ae) ->
{
ViewFrame b=new ViewFrame();
dispose();
};
btnView.addActionListener(a2);

ActionListener a3=(ae) ->
{
UpdateFrame c=new UpdateFrame();
dispose();
};
btnUpdate.addActionListener(a3);

ActionListener a4=(ae) ->
{
DeleteFrame d=new DeleteFrame();
dispose();
};
btnDelete.addActionListener(a4);

setTitle("Employee Management System");
setSize(800,500);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}