<?php

namespace EventBundle\Controller;

use EventBundle\Entity\event;
use EventBundle\EventBundle;
use EventBundle\Form\eventType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class EventController extends Controller
{

    public function ListAction()
    { 
     if($this->getUser()==null)
     {
        return $this->redirectToRoute('fos_user_security_login');
     }

      $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EventBundle:event')->findAll();
        return $this->render('@Event/Event/list.html.twig', array(
            'events' => $events,        ));
    }


    public function newAction(Request $request)
    {
        $event = new event();
        $form = $this->createForm('EventBundle\Form\eventType', $event);
        $form->handleRequest($request);
        $btn=$request->get('ok');
  $dt=date('d/frontlistm/Y');
        if ($btn!=null) {
           // echo '<h1>okoko</h1>';
            $event->upload();
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();

            return $this->redirectToRoute('event_list', array('id' => $event->getIdevent()));
        }

        return $this->render('@Event/Event/new.html.twig', array(
            'event' => $event,
            'dt'=>$dt,
            'form' => $form->createView(),
           
        ));
    }



    public function editAction(Request $request, $id)

    {
        $dt=date('d/frontlistm/Y');
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository("EventBundle:event")->find($id);
        $form=$this->createForm(EventType::class,$event);
        $form->handleRequest($request);
        $btn=$request->get('ok');
           $btnradio=$request->get('img');
       if ($btn!=null) {
            //echo '<h1>okooo</h1>';
        if($btnradio!='garder')
        {
            $event->upload();
        }
        else
        {
            $frm=$request->get('eventbundle_event');
           $event->setImage($frm['image']); 
        }
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute("event_list") ;
        }

        return $this->render("@Event/Event/edit.html.twig",array(
            'form'=>$form->createView(),'dt'=>$dt, 'event' => $event,
        ));

    }


    public function deleteAction($id)
    {
        $events = $this -> getDoctrine() -> getRepository(Event::class) -> find($id);
        $em = $this -> getDoctrine() -> getManager();
        $em -> remove($events);
        $em -> flush();
        return $this -> redirectToRoute("event_list");
    }

    public function showAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $event=$em->getRepository('EventBundle:event')->find($id);

        if (!$event) throw $this->createNotFoundException('La page n\'existe pas.');

        return $this->render('@Event/Event/event_show.html.twig',array(
            'event'=>$event
        ));
    }

    public function frontlistAction()
    {
        $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EventBundle:event')->findAll();
        return $this->render('@Event/Event/front_list.html.twig', array(
            'events' => $events,        ));

    }


    public function GererNBAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $eventnb=$em->getRepository('EventBundle:event')->find($id);
        $nbP=$eventnb->getNbP();
        $q = $em->createQueryBuilder()
            ->update('EventBundle:event', 'evt')
            ->set('evt.NbP', '?1')

            ->where('evt.idevent = ?2')
            ->setParameter(1, $nbP-1)
            ->setParameter(2, $id)
            ->getQuery();
        $p = $q->execute();
        return $this->redirectToRoute('front_list');
    }

    public function AnnulerNBAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $eventnb=$em->getRepository('EventBundle:event')->find($id);
        $nbP=$eventnb->getNbP();
        $q = $em->createQueryBuilder()
            ->update('EventBundle:event', 'evt')
            ->set('evt.NbP', '?1')

            ->where('evt.idevent = ?2')
            ->setParameter(1, $nbP+1)
            ->setParameter(2, $id)
            ->getQuery();
        $p = $q->execute();
        return $this->redirectToRoute('front_list');
    }

}




/**/
  public function afficherMobileAction()
    {
        $em =$this->getDoctrine()->getManager();
        $produit = $em->getRepository("GProduitBundle:Produit")->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);
    }

    public function afficherMobile2Action($user_id)
    {
        $em =$this->getDoctrine()->getManager();
        $produit = $em->getRepository("GProduitBundle:Produit")->findbyUser($user_id);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);
    }

    public function  ajouterProduitMobileAction(Request $request){
        $produit = new Produit() ;
       //$partenaire = new User() ;
        //$categorie = new Categorie() ;


        $em = $this->getDoctrine()->getManager();
       // $partenaire = $em->getRepository("UserBundle:User")->findById($request->get('partenaire'));
        $categorie = $em->getRepository(Categorie::class)->findOneBy(array('id' => $request->get('categorie')));
        $partenaire = $em->getRepository(User::class)->findOneBy(array('id' => $request->get('partenaire')));

        $produit->setNom($request->get('nom')) ;
        $produit->setDescription($request->get('description')) ;
        $produit->setPartenaire($partenaire) ;
        $produit->setImage($request->get('image')) ;
        $produit->setCategorie($categorie) ;
        $produit->setMarque($request->get('marque')) ;
        $produit->setPrix($request->get('prix')) ;
        $produit->setQuantite($request->get('quantite')) ;
        $produit->setReference($request->get('reference')) ;


        dump($produit) ;

        $em = $this->getDoctrine()->getManager();
        $em->persist($produit);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);
    }

    public function SupprimerProduitMobileAction($id){

        $em = $this->getDoctrine()->getManager();
        $produit= $em->getRepository("GProduitBundle:Produit")->find("$id");
        $em->remove($produit);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);

    }

    public function ModifierProduitMobileAction(Request $request )
    {
        $em=$this->getDoctrine()->getManager();
        $produit=$em->getRepository("GProduitBundle:Produit")->find($request->get('id'));

        $categorie = $em->getRepository(Categorie::class)->findOneBy(array('id' => $request->get('categorie')));
        $partenaire = $em->getRepository(User::class)->findOneBy(array('id' => $request->get('partenaire')));

        $produit->setNom($request->get('nom')) ;
        $produit->setDescription($request->get('description')) ;
        $produit->setPartenaire($partenaire) ;
        $produit->setImage($request->get('image')) ;
        $produit->setCategorie($categorie) ;
        $produit->setMarque($request->get('marque')) ;
        $produit->setPrix($request->get('prix')) ;
        $produit->setQuantite($request->get('quantite')) ;
        $produit->setReference($request->get('reference')) ;

        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produit);
        return new JsonResponse($formatted);

    }