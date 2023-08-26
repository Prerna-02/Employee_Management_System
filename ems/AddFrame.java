
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class AddFrame extends JFrame
{
Container c;
JLabel lblid,lblname,lblsalary;
JTextField txtid,txtname,txtsalary;
JButton btnsave,btnback;

AddFrame()
{
c=getContentPane();
c.setBackground(Color.PINK);
c.setLayout(null);

lblid=new JLabel("Enter id : ");
txtid=new JTextField(20);
lblname=new JLabel("Enter name : ");
txtname=new JTextField(20);
lblsalary=new JLabel("Enter salary : ");
txtsalary=new JTextField(20);
btnsave=new JButton("Save");
btnback=new JButton("Back");

Font f=new Font("Courier",Font.BOLD,25);


lblid.setFont(f);
txtid.setFont(f);
lblname.setFont(f);
txtname.setFont(f);
lblsalary.setFont(f);
txtsalary.setFont(f);
btnsave.setFont(f);
btnback.setFont(f);


lblid.setBounds(200,30,180,30);
txtid.setBounds(200,80,180,30);
lblname.setBounds(200,130,180,30);
txtname.setBounds(200,180,180,30);
lblsalary.setBounds(200,230,180,30);
txtsalary.setBounds(200,280,180,30);
btnsave.setBounds(200,330,180,40);
btnback.setBounds(200,380,180,40);


c.add(lblid);
c.add(txtid);
c.add(lblname);
c.add(txtname);
c.add(lblsalary);
c.add(txtsalary);
c.add(btnsave);
c.add(btnback);

// BACK BUTTON
ActionListener a1=(ae) ->
{
MainFrame m=new MainFrame();
dispose();
};
btnback.addActionListener(a1);

// SAVE

ActionListener a2=(ae)->
{
try
{
if(txtid.getText().equals(""))
	throw  new Exception("Id should not be empty");


int id=Integer.parseInt(txtid.getText());
if (id<=0)
{
txtid.setText("");
txtid.requestFocus();
throw new Exception("Id must be greater than zero ");
}
String name=txtname.getText();
if(name.length()<=2)
{
txtname.setText("");
txtname.requestFocus();
throw new Exception("Name must contain atleast 2 character");
}
if(!name.matches("[a-zA-Z ]+"))
{
txtname.setText("");
txtname.requestFocus();
throw new Exception("Name should contain only lettters ");
}
if(txtsalary.getText().equals(""))
	throw new Exception("Salary should not be empty");

int salary=Integer.parseInt(txtsalary.getText());
if(salary<0)
{
txtsalary.setText("");
txtsalary.requestFocus();
throw new Exception("Salary cannot be zero ");
}

if(salary<=8000)
{
txtsalary.setText("");
txtsalary.requestFocus();
throw new Exception("Salary should be minimum 8k ");
}
DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url="jdbc:mysql://localhost:3306/emsaugust23";
String un="root";
String pw="abc456";
Connection con=DriverManager.getConnection(url,un,pw);

String sq="select *from employee where id=?";
		PreparedStatement ps=con.prepareStatement(sq);
		ps.setInt(1,id);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		
		throw new Exception(id+"  Id Already exists ");


String sql="insert into employee values(?,?,?)";
PreparedStatement pst=con.prepareStatement(sql);
pst.setInt(1,id);
pst.setString(2,name);
pst.setDouble(3,salary);
pst.executeUpdate();
JOptionPane.showMessageDialog(c,"Record added");
con.close();

}

catch(SQLException e)
{
JOptionPane.showMessageDialog(c,"issue "+e);
}
catch(NumberFormatException e)
{
JOptionPane.showMessageDialog(c, "id and salary must be digits only ");
}

catch(Exception e)
{
JOptionPane.showMessageDialog(c,e.getMessage());
}

};
btnsave.addActionListener(a2);


setTitle("Add Employee Record ");
setSize(600,500);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}
