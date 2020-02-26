<?php

namespace EventBundle\Controller;


use EventBundle\Entity\event;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use EventBundle\Form\participationType;

class ParticipationController extends Controller
{
    public function GererNBAction($id)
    {

        $em = $this->getDoctrine()->getManager();
        $eventnb=$em->getRepository('EventBundle:event')->find($id);
        $nbP=$eventnb->getNbP();
        echo $nbP;
       /* return $this->render('@Event/Participation/affiche.html.twig',array(
            'event'=>$event   ));
*/

       /* $q = $cnx->createQueryBuilder()
            ->update('pfeBundle:conge', 'cng')
            ->set('cng.etat', '?1')

            ->where('cng.id = ?2')
            ->setParameter(1, 'valider')
            ->setParameter(2, $id)
            ->getQuery();
        $p = $q->execute();
        //$resultat=$cnx->getRepository('pfeBundle:conge')->findAll();
        return new response('demande validé');
        //return $this->render('pfeBundle:Conge:listeconge.html.twig',array('liste'=>$resultat));*/
    }

}
