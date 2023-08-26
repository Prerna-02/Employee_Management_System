import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel lblid;
JTextField txtid;
JButton btndelete,btnback;

DeleteFrame()
{
c=getContentPane();
c.setBackground(Color.PINK);
c.setLayout(null);

lblid=new JLabel("Enter id : ",JLabel.CENTER);
txtid=new JTextField(20);
btndelete=new JButton("Delete");
btnback=new JButton("Back");

Font f=new Font("Courier",Font.BOLD,25);
lblid.setFont(f);
txtid.setFont(f);
btndelete.setFont(f);
btnback.setFont(f);

lblid.setBounds(200,40,150,30);
txtid.setBounds(200,90,120,30);
btndelete.setBounds(200,140,180,40);
btnback.setBounds(200,200,180,40);

c.add(lblid);
c.add(txtid);
c.add(btndelete);
c.add(btnback);

ActionListener a1=(ae) ->{
MainFrame a=new MainFrame();
dispose();
};
btnback.addActionListener(a1);

ActionListener a2=(ae) -> {

try
{

DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url="jdbc:mysql://localhost:3306/emsaugust23";
String un="root";
String pw="abc456";
Connection con=DriverManager.getConnection(url,un,pw);

String sql="delete from employee where id=?";
PreparedStatement ps=con.prepareStatement(sql);
int id=Integer.parseInt(txtid.getText());
ps.setInt(1,id);
int r=ps.executeUpdate();
if(r==1)
{
JOptionPane.showMessageDialog(c,"Record Deleted");
}
else
{
JOptionPane.showMessageDialog(c,"Id does not exists");
}
con.close();
}

catch(SQLException e)
{
JOptionPane.showMessageDialog(c, "issue" +e);
}
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(c,"Invalid input!!!");
}
};

btndelete.addActionListener(a2);

setTitle("Delete Employee");
setSize(600,400);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}

