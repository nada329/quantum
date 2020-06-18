<?php

namespace BibliothequeBundle\Controller;

use BibliothequeBundle\Entity\LivreRating;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use BibliothequeBundle\Entity\Livre;
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

        $livres = $em->getRepository('BibliothequeBundle:Livre')->findAll();

        return $this->render('livre/index.html.twig', array(
            'livres' => $livres,
        ));
    }


    public function indexFAction()
    {
        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('BibliothequeBundle:Categorie')->findAll();
        $livres = $em->getRepository('BibliothequeBundle:Livre')->findAll();

        return $this->render('livre/indexF.html.twig', array(
            'livres' => $livres,'categories'=>$categories
        ));
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function newAction(Request $request)
    {
        $livre = new Livre();
        $form = $this->createForm('BibliothequeBundle\Form\LivreType', $livre);
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


    public function showAction(Request $request,Livre $livre)
    {
        $em = $this->getDoctrine()->getManager();
        $auth_checker = $this->get('security.authorization_checker');
        $rates = $em->getRepository('BibliothequeBundle:LivreRating')->findBy(array('livre'=>$livre->getId()));
        $avis = 0;
        if(count($rates) > 0){
            foreach ($rates as $r){
                $avis += $r->getRating();
            }
            $avis = $avis/ count($rates);
        }
        if( $auth_checker->isGranted('IS_AUTHENTICATED_FULLY')){
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $livreRating = $em->getRepository('BibliothequeBundle:LivreRating')->findOneBy(array('user'=>$user->getId(),'livre'=>$livre->getId()));
            if(empty($livreRating)){
                $livreRating = new LivreRating();
            }
            $form = $this->createForm('BibliothequeBundle\Form\LivreRatingType', $livreRating);
            $form->handleRequest($request);
            if($request->isMethod('POST')){
                $livreRating->setUser($user);
                $livreRating->setLivre($livre);
                $em->persist($livreRating);
                $em->flush();
                return $this->redirectToRoute('livre_show',array('id' => $livre->getId()));
            }
        }else{
            $livreRating = new LivreRating();
            $form = $this->createForm('BibliothequeBundle\Form\LivreRatingType', $livreRating);
            $form->handleRequest($request);
            if($request->isMethod('POST')){
                return $this->redirectToRoute('fos_user_security_login');
            }
        }
        return $this->render('livre/show.html.twig', array(
            'livre' => $livre,
            'form' => $form->createView(),
            'avis'=>$avis,
        ));
    }


    /**
     * @IsGranted("ROLE_ADMIN")
     */
    public function editAction(Request $request, Livre $livre)
    {
        $deleteForm = $this->createDeleteForm($livre);
        $editForm = $this->createForm('BibliothequeBundle\Form\LivreEditType', $livre);
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
            $reservation = $em->getRepository('BibliothequeBundle:Reservation')->findBy(array('livre'=>$livre->getId()));
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
