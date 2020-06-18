<?php

namespace AdministrationBundle\Controller;

use AdministrationBundle\Entity\Eleve;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class EleveController extends Controller
{
    /**
     * @Route("/listel")
     */
    public function listelAction()
    {
        $em = $this->getDoctrine()->getManager();

        $eleveslist = $em->getRepository('AdministrationBundle:Eleve')->findAll();
        return $this->render('@Administration/Eleve/listel.html.twig', array(
            "eleves" => $eleveslist,
        ));

    }


}
