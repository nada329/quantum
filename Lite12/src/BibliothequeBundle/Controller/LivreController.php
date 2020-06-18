<?php

namespace BibliothequeBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use UserBundle\Entity\Livre;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class LivreController extends Controller
{

    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $livres = $em->getRepository('UserBundle:Livre')->findAll();

        return $this->render('livre/index.html.twig', array(
            'livres' => $livres,
        ));
    }


    public function indexFAction()
    {
        $em = $this->getDoctrine()->getManager();

        $livres = $em->getRepository('UserBundle:Livre')->findAll();

        return $this->render('livre/indexF.html.twig', array(
            'livres' => $livres,
        ));
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function newAction(Request $request)
    {
        $livre = new Livre();
        $form = $this->createForm('UserBundle\Form\LivreType', $livre);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $file=$livre->getPhoto();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move(
                $this->getParameter('livre_images'),$fileName
            );
            $livre->setPhoto($fileName);
            $em->persist($livre);
            $em->flush();
            return $this->redirectToRoute('livre_index');
        }
        return $this->render('livre/new.html.twig', array(
            'livre' => $livre,
            'form' => $form->createView(),
        ));
    }


    public function showAction(Livre $livre)
    {
        $deleteForm = $this->createDeleteForm($livre);

        return $this->render('livre/show.html.twig', array(
            'livre' => $livre,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function editAction(Request $request, Livre $livre)
    {
        $deleteForm = $this->createDeleteForm($livre);
        $editForm = $this->createForm('UserBundle\Form\LivreEditType', $livre);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('livre_index');

        }

        return $this->render('livre/edit.html.twig', array(
            'livre' => $livre,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function deleteAction(Request $request, Livre $livre)
    {
        $form = $this->createDeleteForm($livre);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $reservation = $em->getRepository('UserBundle:Reservation')->findBy(array('livre'=>$livre->getId()));
            if($reservation){
                $this->addFlash("warning", "livre est reservé");
            }else{
                $em->remove($livre);
                $em->flush();
                $this->addFlash("success", "liver suprrimé");
            }

        }

        return $this->redirectToRoute('livre_index');
    }


    private function createDeleteForm(Livre $livre)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('livre_delete', array('id' => $livre->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
