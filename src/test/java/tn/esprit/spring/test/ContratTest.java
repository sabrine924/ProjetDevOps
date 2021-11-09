package tn.esprit.spring.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.services.ContratServiceImpl;
import tn.esprit.spring.services.EmployeServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ContratTest {
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	EmployeRepository employeRepoistory;
	@Autowired
	EmployeServiceImpl employeServiceImpl;
	@Autowired
	
	ContratServiceImpl icontratservice;
    private static final Logger l = LogManager.getLogger(ContratTest.class);


	@Test
	public void TestAddContrat() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2021-10-07"); //date contrat 07 october 2021
		Contrat contrat = new Contrat(d, "CDD", 500); //type de contrat cdd et salaire 500
	int idcontrat = employeServiceImpl.ajouterContrat(contrat);

		//assertEquals(4, idcontrat);
		l.info("contrat a ete ajoute avec id : " + idcontrat);
	}

	/*@Test
	public void testAffecterContratAEmployet() {

		employeServiceImpl.affecterContratAEmploye(1,5); //contrat id = 2, employe id= 5
		Employe employe = employeRepoistory.findById(1).get(); 
		int idContrat = employe.getContrat().getReference();
		assertEquals(1, idContrat);
		  l.info("idContrat not null", +idContrat);

	}*/
	@Test
	public void tesUpdateContrat() {
		Contrat contrat = icontratservice.getContratById(3);
		contrat.setSalaire(900);
		int contratId = employeServiceImpl.ajouterContrat(contrat);
		Contrat updatedcontrat = icontratservice.getContratById(contratId);
		assertThat(updatedcontrat.getSalaire()).isEqualTo(contrat.getSalaire());
	}
	@Test
	public void testFindContratById() {
		Contrat contrat = icontratservice.getContratById(3);
		assertThat(contrat.getReference()).isEqualTo(3);
	}
	@Test
	public void testGetListContrats() {
		ArrayList<Contrat> entreprises = (ArrayList<Contrat>) icontratservice.getAllContrats();
		assertThat(entreprises.size()).isPositive();
		  l.info("You can see your entreprises list :) ");
	}

 /*@Test
	public void TestDeleteContratById() {
		Contrat contrat = contratRepoistory.findById(6).get();
		
		
		if(contrat.getReference()!=0) {
	    	
			employeServiceImpl.deleteContratById(4);
			assertThat(employeRepoistory.existsById(1)).isFalse();//confirm that employe has beeen deleted
	    	 }
	    	 else {
	    		 assertNull(contrat);
	    	 }
	
	}*/

	

/*	@Test
	public void TestdeleteAllContratJPQL() {
		employeServiceImpl.deleteAllContratJPQL();
		List<Contrat> contrat = (List<Contrat>) contratRepoistory.findAll();
		assertEquals(0, contrat.size());
	}*/

}