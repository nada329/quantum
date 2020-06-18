<?php

namespace EventBundle\Controller;

use EventBundle\Entity\event;
use EventBundle\EventBundle;
use EventBundle\Form\EventType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class EventController extends Controller
{

    public function ListAction()
    {   $em = $this->getDoctrine()->getManager();

        $events = $em->getRepository('EventBundle:event')->findAll();
        return $this->render('@Event/Event/list.html.twig', array(
            'events' => $events,        ));
    }


    public function newAction(Request $request)
    {
        $event = new event();
        $form = $this->createForm('EventBundle\Form\eventType', $event);
        $form->handleRequest($request);
  $dt=date('d/frontlistm/Y');
        if ($form->isSubmitted() && $form->isValid()) {
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
        $em=$this->getDoctrine()->getManager();
        $event=$em->getRepository("EventBundle:event")->find($id);
        $form=$this->createForm(EventType::class,$event);
        $form->handleRequest($request);
        if ($form->isSubmitted()&& $form->isValid()) {
            $event->upload();
            $em = $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute("event_list") ;
        }

        return $this->render("@Event/Event/edit.html.twig",array(
            'form'=>$form->createView()
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
