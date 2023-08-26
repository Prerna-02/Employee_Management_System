import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class ViewFrame extends JFrame
{
Container c;
TextArea taData;
JButton btnback;

ViewFrame()
{
c=getContentPane();
c.setBackground(Color.PINK);
c.setLayout(new FlowLayout());

taData=new TextArea(10,50);
btnback=new JButton("Back");

Font f=new Font("Courier",Font.BOLD,25);

btnback.setFont(f);


btnback.setBounds(300,500,180,40);

c.add(taData);
c.add(btnback);

StringBuffer data=new StringBuffer();
try
{

DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
String url="jdbc:mysql://localhost:3306/emsaugust23";
String un="root";
String pw="abc456";
Connection con=DriverManager.getConnection(url,un,pw);

String sql="select *from employee";
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(sql);
while(rs.next())
{

int id=rs.getInt(1);
String name=rs.getString(2);
double salary=rs.getDouble(3);
data.append("ID -> " +id+ "   NAME  ->  " +name+ "   SALARY -> " +salary + "\n");
}

taData.setText(data.toString());
con.close();

}

catch(SQLException e )
{
JOptionPane.showMessageDialog(c,"issue " +e);
}

c.add(taData);
c.add(btnback);


ActionListener a1=(ae) ->
{
MainFrame a=new MainFrame();
dispose();
};
btnback.addActionListener(a1);

setTitle("View Employee Record");
setSize(600,500);
setResizable(false);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}