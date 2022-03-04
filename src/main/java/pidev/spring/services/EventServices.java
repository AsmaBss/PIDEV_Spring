package pidev.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import pidev.spring.entities.Event;
import pidev.spring.entities.EventType;
import pidev.spring.entities.Event;
import pidev.spring.entities.User;
import pidev.spring.repositories.EventRepository;
import pidev.spring.repositories.UserRepository;

@Service
public class EventServices {
	@Autowired
	EventRepository EventRepository;
	@Autowired
	UserRepository UserRepository;

	//Ajout
	public Event addEvent(Event c) {
		return EventRepository.save(c);
	}
	//Delete
	public void deleteEvent(int id) {
		EventRepository.deleteById(id);
	}
	//FindById
	public Event retrieveEvent(int id) {
		return EventRepository.findById(id).orElse(null);
	}
	//Update
	public Event updateEvent(Event c) {
		Event event = EventRepository.findById((int) c.getId()).orElse(null);
		event.setDateEnd(c.getDateEnd());
		event.setDateStart(c.getDateStart());
		event.setDescription(c.getDescription());
		event.setNbrplace(c.getNbrplace());
		event.setTitle(c.getTitle());
		event.setTrouphy(c.isTrouphy());
		event.setType(c.getType());
		return EventRepository.save(event);
	}
	//FindAll
	public List<Event> listedesEvents() {
		return EventRepository.findAll();
	}
	//Find By Title
	public Event FindEventByTitle(String Title) {
		return EventRepository.findByTitle(Title);
	}
	//Find By DateStart
	public List<Event> FindEventByDateStart(Date DateStart) {
		return EventRepository.findByDateStart(DateStart);
	}
	//Find By DateEnd
	public List<Event> FindEventByDateEnd(Date DateEnd) {
		return EventRepository.findByDateEnd(DateEnd);
	}
	//Find By Type
	public List<Event> FindEventByType(EventType EventType) {
		return EventRepository.findByType(EventType);
	}

	/*
	public void ajouterEtaffecterListeevent (List<Event> lb, Long idCentre) {
		//ajouter à la fois la liste des events suivante
		EventRepository.saveAll(lb);
		
		//l’affecter au centre commercial crée dans la question
		CentreCommercial C=CR.findById(idCentre).orElse(null);
		C.getEvents().addAll(lb);
		CR.save(C);
	}*/
	
	//Affecter Event To User
		public User AffecterEventToUser (int badge, int userid) {
			//l’affecter au centre commercial crée dans la question
			Event Event=EventRepository.findById(badge).orElse(null);
			User User=UserRepository.findById(userid).orElse(null);
			//User.setEvent(Event);
			return UserRepository.save(User);
			
		}
		//SortEventBy Id Asc
		public List<Event> SortEventsByIdAsc(){
			return EventRepository.findAllByOrderByIdAsc();
		}
		//SortEventBy Id Desc
		public List<Event> SortEventsByIdDesc(){
			return EventRepository.findAllByOrderByIdDesc();
		}
		//SortEventBy Title Asc
		public List<Event> SortEventsByTitleAsc(){
			return EventRepository.findAllByOrderByTitleAsc();
		}
		//SortEventBy Title Desc
		public List<Event> SortEventsByTitleDesc(){
			return EventRepository.findAllByOrderByTitleDesc();
		}
		//SortEventBy DateStart Asc
		public List<Event> SortEventsByDateStartAsc(){
			return EventRepository.findAllByOrderByDateStartAsc();
		}
		//SortEventBy DateStart Desc
		public List<Event> SortEventsByDateStartDesc(){
			return EventRepository.findAllByOrderByDateStartDesc();
		}
		//SortEventBy DateEnd Asc
		public List<Event> SortEventsByDateEndAsc(){
			return EventRepository.findAllByOrderByDateEndAsc();
		}
		//SortEventBy DateStart Desc
		public List<Event> SortEventsByDateEndDesc(){
			return EventRepository.findAllByOrderByDateEndDesc();
		}
		//SortEventBy NbrPlace Asc
		public List<Event> SortEventsByNbrplaceAsc(){
			return EventRepository.findAllByOrderByNbrplaceAsc();
		}
		//SortEventBy Nbrplace Desc
		public List<Event> SortEventsByNbrplaceDesc(){
			return EventRepository.findAllByOrderByNbrplaceDesc();
		}
		//SortEventBy Type Asc
		public List<Event> SortEventsByTypeAsc(){
			return EventRepository.findAllByOrderByTypeAsc();
		}
		//SortEventBy Type Desc
		public List<Event> SortEventsByTypeDesc(){
			return EventRepository.findAllByOrderByTypeDesc();
		}
		//SortEventBy Type Asc
		public List<Event> SortEventsByTrouphyAsc(){
			return EventRepository.findAllByOrderByTrouphyAsc();
		}
		//SortEventBy Type Desc
		public List<Event> SortEventsByTrouphyDesc(){
			return EventRepository.findAllByOrderByTrouphyDesc();
		}
		//SortEventBy Description Asc
		public List<Event> SortEventsByDescriptionAsc(){
			return EventRepository.findAllByOrderByDescriptionAsc();
		}
		//SortEventBy Title Desc
		public List<Event> SortEventsByDescriptionDesc(){
			return EventRepository.findAllByOrderByDescriptionDesc();
		}
		//SortEventBy Top 10 Title Asc
		public List<Event> SortEventsTop10ByOrderByTitleAsc(){
			return EventRepository.findTop10ByOrderByTitleAsc();
		}
		//SortEventBy Top 10 Title Desc
		public List<Event> SortEventsTop10ByOrderByTitleDesc(){
				return EventRepository.findTop10ByOrderByTitleDesc();
		}
//Multiple Critére Search 
		//find DateStart + DateEnd
		public List<Event> findByDateStartAndDateEnd(Date StartDate,Date DateEnd){
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findAllByDateStartAndDateEnd(StartDate,DateEnd);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Starting Date or Ending Date ");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByDateStartAndDateEnd");
			}
			return E;
		}
		//findBy DateStart + Trouphy
		public List<Event> findByDateStartAndTrouphy(Date StartDate,boolean trouphy){
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByDateStartAndTrouphy(StartDate,trouphy);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Starting Date or Trouphy");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByDateStartAndTrouphy");
			}
			return E;
		}
		//findBy DateEnd + Trouphy
				public List<Event> findByDateEndAndTrouphy(Date StartEnd,boolean trouphy){
					List<Event> E = new ArrayList<Event>();
					try {
						List<Event> TryE = new ArrayList<Event>();
						TryE=EventRepository.findByDateEndAndTrouphy(StartEnd,trouphy);
						if(TryE.size()==0) {
							System.out.println("There Are No Events In The DataBase With The Provided Ending Date or Trouphy");
						}else {
							System.out.println("Events Avariable : ");
							System.out.println(TryE);
							E=TryE;
						}
					}catch(Exception e ) {
						System.out.println("Error Could Not Retrive findByDateEndAndTrouphy");
					}
					return E;
				}
		//findBy DateStart + DateEnd + Trouphy 
		public List<Event> findByDateStartAndDateEndAndTrouphy(Date StartDate,Date DateEnd,boolean trouphy){
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByDateStartAndDateEndAndTrouphy(StartDate,DateEnd,trouphy);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Starting Date or Ending Date Or Trouphy.");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByDateStartAndDateEndAndTrouphy");
			}
			return E;
		}
		//findBy DateStart + DateEnd + Trouphy + Type
		public List<Event> findByDateStartAndDateEndAndTrouphyAndType(Date StartDate,Date DateEnd,boolean trouphy,EventType Type){
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByDateStartAndDateEndAndTrouphyAndType(StartDate,DateEnd,trouphy,Type);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Starting Date , Ending Date , Trouphy Or Type.");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByDateStartAndDateEndAndTrouphyAndType");
			}
			return E;
		}
		//findBy Type + Trouphy 
		public List<Event> findByTypeAndTrouphy(EventType type, boolean trouphy){
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByTypeAndTrouphy(type,trouphy);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Type Or Trouphy");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByTypeAndTrouphy");
			}
			return E;
		}
		
		public List<Event> findByDateStartAndType(Date startDate, EventType type) {
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByDateStartAndType(startDate,type);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Type Or StartDate");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive findByDateStartAndType");
			}
			return E;
		}
		public List<Event> FindByDateEndAndType(Date dateEnd, EventType type) {
			List<Event> E = new ArrayList<Event>();
			try {
				List<Event> TryE = new ArrayList<Event>();
				TryE=EventRepository.findByDateEndAndType(dateEnd,type);
				if(TryE.size()==0) {
					System.out.println("There Are No Events In The DataBase With The Provided Type Or Ending Date");
				}else {
					System.out.println("Events Avariable : ");
					System.out.println(TryE);
					E=TryE;
				}
			}catch(Exception e ) {
				System.out.println("Error Could Not Retrive FindByDateEndAndType");
			}
			return E;
		}
		
		/////
		//Nombre Total Events
		public int TotalNumberEvents() {
			int nbr = 0;
			try {
				int trynbr;
				trynbr=EventRepository.findAll().size();
				if(trynbr==0) {
					System.out.println("There Are No Events In The DataBase, please Consider Adding Somme Of Them To Start Counting.");
				}else {
					System.out.println("Total Number Of Events is : "+trynbr);
					nbr=trynbr;
				}
			}catch(Exception e){
				System.out.println("Error Could Not Retrive TotalNumberEvents");
			}
			return nbr;
		}
		//Nombre CHALLENGRE
		public int TotalNumberEventsChallenge() {
			int nbr = 0;
			try {
				int trynbr;
				trynbr=EventRepository.findByType(EventType.CHALLENGE).size();
				if(trynbr==0) {
					System.out.println("There Are No Events With The Type CHALLENGE In The DataBase, please Consider Adding Somme Of Them To Start Counting.");
				}else {
					System.out.println("Total Number Of Events Where The Type is CHALLENGE : "+trynbr);
					nbr=trynbr;
				}
			}catch(Exception e){
				System.out.println("Error Could Not Retrive TotalNumberEventsCHALLENGE");
			}
			return nbr;
		}
		//Nombre FORMATION
		public int TotalNumberEventsFormation() {
			int nbr = 0;
			try {
				int trynbr;
				trynbr=EventRepository.findByType(EventType.FORMATION).size();
				if(trynbr==0) {
					System.out.println("There Are No Events With The Type FORMATION In The DataBase, please Consider Adding Somme Of Them To Start Counting.");
				}else {
					System.out.println("Total Number Of Events Where The Type is FORMATION : "+trynbr);
					nbr=trynbr;
				}
			}catch(Exception e){
				System.out.println("Error Could Not Retrive TotalNumberEventsFORMATION");
			}
			return nbr;
		}
		//Number of Events With Trouphies=true
		public int TotalNumberEventsTrouphyTrue() {
			int nbr = 0;
			try {
				int trynbr;
				trynbr=EventRepository.findByTrouphy(true).size();
				if(trynbr==0) {
					System.out.println("There Are No Events With Trouphies In The DataBase, please Consider Adding Somme Of Them To Start Counting.");
				}else {
					System.out.println("Total Number Of Events With Trouphies : "+trynbr);
					nbr=trynbr;
				}
			}catch(Exception e){
				System.out.println("Error Could Not Retrive TotalNumberEventsTrouphyTrue");
			}
			return nbr;
		}
		//Number of Events With Trouphies=true
		public int TotalNumberEventsTrouphyFalse() {
			int nbr = 0;
			try {
				int trynbr;
				trynbr=EventRepository.findByTrouphy(false).size();
				if(trynbr==0) {
					System.out.println("There Are No Events With No Trouphies In The DataBase, please Consider Adding Somme Of Them To Start Counting.");
				}else {
					System.out.println("Total Number Of Events With No Trouphies : "+trynbr);
					nbr=trynbr;
				}
			}catch(Exception e){
				System.out.println("Error Could Not Retrive TotalNumberEventsTrouphyFalse");
			}
			return nbr;
		}
		
		

		}
