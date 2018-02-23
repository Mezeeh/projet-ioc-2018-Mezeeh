package ca.qc.cgmatane.informatique.outilseisme.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SeismeDAO {

	protected List<String> informations;
	
	public List<String> rechercherInformationsVille(String nomVille){
		if(nomVille.contains(" "))
			nomVille = nomVille.replaceAll(" ", "%20");

		String adresseTremblementsVille = "https://soda.demo.socrata.com/resource/6yvf-kk3n.xml?source=pr&$where=region%20like%20%27%25" + nomVille + "%25%27";
		String xmlTremblementsVille = "";
		String informationVille = "";

		informations = new ArrayList<String>();

		try {
			URL urlTremblementsVille = new URL(adresseTremblementsVille);
			InputStream fluxTremblementsVille = urlTremblementsVille.openStream();
			BufferedReader lecteurTremblementsVille = new BufferedReader(new InputStreamReader(fluxTremblementsVille));

			String ligne = "";
			while((ligne = lecteurTremblementsVille.readLine()) != null)
				xmlTremblementsVille += ligne;

			//System.out.println("XML Tremblements par ville : " + xmlTremblementsVille);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = parseur.parse(new StringBufferInputStream(xmlTremblementsVille));

			NodeList listeNoeudItem = document.getElementsByTagName("row");

			for(int position = 0; position < listeNoeudItem.getLength(); position++){
				informationVille = "";
				Element row = (Element)listeNoeudItem.item(position);
				String region = null != row.getElementsByTagName("region").item(0) ? ((Element)row.getElementsByTagName("region").item(0)).getTextContent() : null;
				String depth = null != row.getElementsByTagName("depth").item(0) ? ((Element)row.getElementsByTagName("depth").item(0)).getTextContent() : null;
				String magnitude = null != row.getElementsByTagName("magnitude").item(0) ? ((Element)row.getElementsByTagName("magnitude").item(0)).getTextContent() : null;
				String location = null != row.getElementsByTagName("location").item(0) ? ((Element)row.getElementsByTagName("location").item(0)).getTextContent() : null;
				location = null != location ? location.substring(location.indexOf(" ") + 2, location.length() - 1) : null;

				//System.out.println(null != location ? region + " | Profondeur : " + depth + " | Magnitude : " + magnitude + " | Location : " + location : region + " | Profondeur : " + depth + " | Magnitude : " + magnitude);
				if(null != region) informationVille += region.substring(0, region.indexOf("of") - 1);
				if(null != depth) informationVille += " | Profondeur : " + depth;
				if(null != magnitude) informationVille += " | Magnitude : " + magnitude;
				if(null != location) informationVille += " | Location : " + location;

				informations.add(informationVille);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return informations;
	}

	public List<String> rechercherInformationMondial(){
		String adresseTremblementsMondial = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=xml&starttime=2017-01-25&endtime=2017-01-30";
		String xmlTremblementsMondial = "";
		String informationMondial = "";

		informations = new ArrayList<String>();

		try {
			URL urlTremblementsMondial = new URL(adresseTremblementsMondial);
			InputStream fluxTremblementsMondial = urlTremblementsMondial.openStream();
			BufferedReader lecteurTremblementsMondial = new BufferedReader(new InputStreamReader(fluxTremblementsMondial));

			String ligne = "";
			while((ligne = lecteurTremblementsMondial.readLine()) != null)
				xmlTremblementsMondial += ligne;

			//System.out.println("XML Tremblements par ville : " + xmlTremblementsVille);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = parseur.parse(new StringBufferInputStream(xmlTremblementsMondial));

			NodeList listeNoeudItem = document.getElementsByTagName("event");

			for(int position = 0; position < listeNoeudItem.getLength(); position++){
				informationMondial = "";
				Element event = (Element)listeNoeudItem.item(position);
				String region = null != event.getElementsByTagName("text").item(0) ? ((Element)event.getElementsByTagName("text").item(0)).getTextContent() : null;
				String coordonnees = null != event.getElementsByTagName("longitude").item(0) && null != event.getElementsByTagName("latitude").item(0) ?
						((Element)event.getElementsByTagName("longitude").item(0)).getTextContent() + " " + ((Element)event.getElementsByTagName("latitude").item(0)).getTextContent() : null;
				String depth = null != event.getElementsByTagName("depth").item(0) ? ((Element)event.getElementsByTagName("depth").item(0).getFirstChild()).getTextContent() : null;
				String date = null != event.getElementsByTagName("time").item(0) ? ((Element)event.getElementsByTagName("time").item(0)).getTextContent() : null;

				//System.out.println(date + " | " + region + " | Profondeur : " + depth + " | Location : " + coordonnees);
				if(null != date) informationMondial += date;
				if(null != region) informationMondial += " | " + region;
				if(null != depth) informationMondial += " | Profondeur : " + depth;
				if(null != coordonnees) informationMondial += " | Location : " + coordonnees;

				informations.add(informationMondial);
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return informations;
	}
}
