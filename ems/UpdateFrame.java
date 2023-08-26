import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel lblid,lblname,lblsalary;
JTextField txtid,txtname,txtsalary;
JButton btnupdate,btnback;

UpdateFrame()
{
c=getContentPane();
c.setBackground(Color.PINK);
c.setLayout(null);

lblid=new JLabel("Enter id :");
txtid=new JTextField(20);
lblname=new JLabel("Enter name: ");
txtname=new JTextField(20);
lblsalary=new JLabel("Enter salary :");
txtsalary=new JTextField(30);
btnupdate=new JButton("Update");
btnback=new JButton("Back");

Font f=new Font("Courier",Font.BOLD,25);
lblid.setFont(f);
txtid.setFont(f);
lblname.setFont(f);
txtname.setFont(f);
lblsalary.setFont(f);
txtsalary.setFont(f);
btnupdate.setFont(f);
btnback.setFont(f);

lblid.setBounds(200,30,180,30);
txtid.setBounds(200,80,180,30);
lblname.setBounds(200,130,180,30);
txtname.setBounds(200,180,180,30);
lblsalary.setBounds(200,230,180,30);
txtsalary.setBounds(200,280,180,30);
btnupdate.setBounds(200,330,180,40);
btnback.setBounds(200,380,180,40);


c.add(lblid);
c.add(txtid);
c.add(lblname);
c.add(txtname);
c.add(lblsalary);
c.add(txtsalary);
c.add(btnupdate);
c.add(btnback);

ActionListener a1=(ae) ->
{
MainFrame a=new MainFrame();
dispose();
};
btnback.addActionListener(a1);	

ActionListener a2=(ae)->
{
try
{
if(txtid.getText().equals(""))
throw new Exception("Id cannot be empty ");

int id=Integer.parseInt(txtid.getText());
if(id<=0)
{
txtid.setText("");
txtid.requestFocus();
throw new Exception("Id should be greater than zero!");
}

DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url="jdbc:mysql://localhost:3306/emsaugust23";
String un="root";
String pw="abc456";
Connection con=DriverManager.getConnection(url,un,pw);
String sq="select *From employee where id=?";
PreparedStatement ps=con.prepareStatement(sq);
ps.setInt(1,id);
ResultSet rs=ps.executeQuery();
if(rs.next())
{

String name=txtname.getText();
if(name.length()<=2)
{
txtname.setText("");
txtname.requestFocus();
throw new Exception("Name must contain atleast two character");
}

if(!name.matches("[A-z ]+"))
{
txtname.setText("");
txtname.requestFocus();
throw new Exception("Name should contain only letters ");
}

if(txtsalary.getText().equals(""))
throw new Exception("Salary cannot be empty");

int salary=Integer.parseInt(txtsalary.getText());

if(salary<0)
{
txtsalary.setText("");
txtsalary.requestFocus();
throw new Exception("Salary cannot be negative ");
}

if (salary<=8000)
{
txtsalary.setText("");
txtsalary.requestFocus();
throw new Exception("Salary should be minmum 8k ");
}

String sql="update employee set name=? ,salary=?  where id=? ";

PreparedStatement pst=con.prepareStatement(sql);

pst.setString(1,name);
pst.setDouble(2,salary);
pst.setInt(3,id);
pst.executeUpdate();
con.close();
JOptionPane.showMessageDialog(c, "Record Updated");
}
else
throw new Exception(id+ "  does not exists");
con.close();

}

catch(SQLException e)
{
JOptionPane.showMessageDialog(c, "issue" +e);
}
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(c,"Id and Salary must be in digit format");
}

catch(Exception e)
{
JOptionPane.showMessageDialog(c,e.getMessage());
}
};
btnupdate.addActionListener(a2);

setTitle("Update Employee Record");
setSize(600,500);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}	