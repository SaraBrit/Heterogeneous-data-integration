package data;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

public class interface_traitement extends JPanel {
	
	 static String[]atri= {"Zip","stdhours","PayRate","ManagerName","deptid","Gender","paygroup","hiredate","uuid","jobentrydt","saladminplan"
				,"jobfamily","Department","fte","DOB","MaritalDesc","Division","id","GrossPayReceived2017","agerange",
				"EmployeeSource","CitizenDesc","address","race","jobtitle","annualrt","grade","name","jobcode","positionnbr","location"
				,"step","position","eeojobgroup"
		};
	 
	public static void main(String[]args)  throws  	    IOException{
		 
		 

				File fXmlFile = new File("results.xml");
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder;
				try {
					dBuilder = dbFactory.newDocumentBuilder();
			 
				Document doc = dBuilder.parse(fXmlFile);
				 
						
		 
				doc.getDocumentElement().normalize();

				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				
				NodeList nList = doc.getElementsByTagName("Employee");
	 
				   DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
				      DocumentBuilder build = dFact.newDocumentBuilder();
				      Document dooc = build.newDocument();
				      Element root = dooc.createElement("liste_Employee");
				      dooc.appendChild(root);
				int count=0;
				for (int temp = 0; temp < nList.getLength()||temp<96 ; temp++) {
					 Element emp = dooc.createElement("Employee");
					  root.appendChild(emp);
				    count++;
					for(int val=0;val<atri.length;val++) {
						Node nNode = nList.item(val);
						Element  eElement = (Element) (nNode);
						String name=(eElement).getElementsByTagName(atri[val]).item(temp).getTextContent();
					    Element  neww=dooc.createElement(String .valueOf(atri[val]));
					    neww.appendChild(dooc.createTextNode((String.valueOf(name))));
			      emp.appendChild(neww);
					 
						 
						
						
					}
					
					 
				 
				}
			 
			 
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
			      Transformer transformer;
			
					transformer = transformerFactory.newTransformer();
				 
			      DOMSource source = new DOMSource(dooc);
			      StreamResult result = new StreamResult(new File("StylePaper.xml"));

					transformer.transform(source, result);
					
					
				
			//begin the treatement of interface
					File fXm = new File("StylePaper.xml");
					DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
					DocumentBuilder dB;
					
						dB = dbF.newDocumentBuilder();
				 
					Document doct = dB.parse(fXm);
					 
							
			 
					doct.getDocumentElement().normalize();

					System.out.println("Root element of new  :" + doct.getDocumentElement().getNodeName());
					
					NodeList nL = doct.getElementsByTagName("Employee");
					String [][] vals=new String[nL.getLength()][atri.length];
					for(int j=0;j<nL.getLength();j++) {
						Node nNod= nL.item(j);
						Element  eElem = (Element) (nNod);
						for(int at=0;at<atri.length;at++) {
						 
							vals[j][at]=(eElem).getElementsByTagName(atri[at]).item(0).getTextContent();
						}
						
					}
	             JTable tab=new JTable(vals,atri);
	            tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	              JScrollPane pane=new JScrollPane(tab);
	            
	              
	            
              JFrame frame=new JFrame();
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              frame.add(pane,BorderLayout.CENTER);
	          frame.setSize(300, 300);
	          frame.setVisible(true);
				}catch(NullPointerException d){d.printStackTrace();} catch (ParserConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  catch(Exception e)
			{e.printStackTrace();}
			 
				}

	
	 
	    
	private static Node Search_Values(String Query) {
		String values;
	  try {
			File fXm = new File("StylePaper.xml");
			DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
			DocumentBuilder dB;
			
				dB = dbF.newDocumentBuilder();
		 
			Document doct = dB.parse(fXm);
			 
					
	 
			doct.getDocumentElement().normalize();
			
			NodeList nL = doct.getElementsByTagName("Employee");
			for(int j=0;j<nL.getLength();j++) {
				Node nNod= nL.item(j);
				Element  eElem = (Element) (nNod);
				for(int at=0;at<atri.length;at++) {
				 
					values=(eElem).getElementsByTagName(atri[at]).item(0).getTextContent();
					if(values.contains(Query)) {
						Node no=doct.importNode(nNod, true);
						return no;
					}
					if(Query.contains(values)) {
						Node no2=doct.importNode(nNod, true);
						return no2;
					}
				}
				
			}
			
	  }catch(NullPointerException d){d.printStackTrace();} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  catch(Exception e)
	{e.printStackTrace();}
	return null;
	  	}
 
				 
 																																																																																																																		
}
