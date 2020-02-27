<?php

namespace BibliothequeBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use BibliothequeBundle\Entity\Livre;
use BibliothequeBundle\Entity\Reservation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Reservation controller.
 *
 */
class ReservationController extends Controller
{
    /**
     * Lists all reservation entities.
     *
     */
    public function reserverAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('BibliothequeBundle:Reservation')->findAll();

        return $this->render('reservation/index.html.twig', array(
            'reservations' => $reservations,
        ));
    }


    /**
     * @IsGranted("IS_AUTHENTICATED_FULLY")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $reservations = $em->getRepository('BibliothequeBundle:Reservation')->findBy(array('user'=>$user->getId()));
        return $this->render('reservation/index.html.twig', array(
            'reservations' => $reservations,
        ));
    }


    public function indexBackAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('BibliothequeBundle:Reservation')->findBy(array('state'=>'request'));
        return $this->render('reservation/indexBack.html.twig', array(
            'reservations' => $reservations,
        ));
    }
    public function getReservationAction() {
        $em = $this->getDoctrine()->getManager();
        $reservations = $em->getRepository('BibliothequeBundle:Reservation')->findAll();
        return $this->render('reservation/showReservation.html.twig',array(
            'reservations' => $reservations
        ));
    }



    public function declineReservationAction(Reservation $reservation){
        $em = $this->getDoctrine()->getManager();
        $reservation->setState('refus');
        $em->persist($reservation);
        $em->flush();
        return $this->redirectToRoute('reservation_liste');
    }

    public function acceptReservationAction(Reservation $reservation){
        $em = $this->getDoctrine()->getManager();
        $reservation->setState('accepted');
        $livre = $em->getRepository('BibliothequeBundle:Livre')->find($reservation->getLivre()->getId());
        $livre->setQuantity( $livre->getQuantity()-1);
        $em->persist($livre);
        $em->persist($reservation);
        $em->flush();
        return $this->redirectToRoute('reservation_liste');

    }

    public function indexRenderAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reservations = $em->getRepository('BibliothequeBundle:Reservation')->findBy(array('state'=>'accepted'));
        return $this->render('reservation/indexRender.html.twig', array(
            'reservations' => $reservations,
        ));
    }


    public function renderAction(Reservation $reservation){
        $em = $this->getDoctrine()->getManager();
        $reservation->setState('rendu');
        $livre = $em->getRepository('BibliothequeBundle:Livre')->find($reservation->getLivre()->getId());
        $livre->setQuantity( $livre->getQuantity()+1);
        $em->persist($livre);
        $em->persist($reservation);
        $em->flush();
        return $this->redirectToRoute('reservation_renderIndex');
    }

    /**
     * @IsGranted("IS_AUTHENTICATED_FULLY")
     */
    public function newAction(Request $request,Livre $livre)
    {
        $reservation = new Reservation();
        $form = $this->createForm('BibliothequeBundle\Form\ReservationType', $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $reservation->setLivre($livre);
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $reservation->setUser($user);
            $reservation->setState("request");
            $em->persist($reservation);
            $em->flush();

            return $this->redirectToRoute('reservation_index');
        }

        return $this->render('reservation/new.html.twig', array(
            'reservation' => $reservation,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a reservation entity.
     *
     */
    public function showAction(Reservation $reservation)
    {
        $deleteForm = $this->createDeleteForm($reservation);

        return $this->render('reservation/show.html.twig', array(
            'reservation' => $reservation,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing reservation entity.
     *
     */
    public function editAction(Request $request, Reservation $reservation)
    {
        $deleteForm = $this->createDeleteForm($reservation);
        $editForm = $this->createForm('BibliothequeBundle\Form\ReservationType', $reservation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reservation_edit', array('id' => $reservation->getId()));
        }

        return $this->render('reservation/edit.html.twig', array(
            'reservation' => $reservation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a reservation entity.
     *
     */
    public function deleteAction(Request $request, Reservation $reservation)
    {
        $form = $this->createDeleteForm($reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reservation);
            $em->flush();
        }

        return $this->redirectToRoute('reservation_index');
    }

    /**
     * Creates a form to delete a reservation entity.
     *
     * @param Reservation $reservation The reservation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reservation $reservation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('reservation_delete', array('id' => $reservation->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
