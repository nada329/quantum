<?php

namespace AdministrationBundle\Controller;

use AdministrationBundle\Entity\Note;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class NoteController extends Controller
{

    public function ListNAction()
    {
        $em = $this->getDoctrine()->getManager();

        $noteslist = $em->getRepository('AdministrationBundle:Note')->findAll();
        return $this->render('@Administration/Note/listN.html.twig', array(
            "notes" => $noteslist,
        ));
    }

    public function calculAction(Request $request)
    {
        $note = new Note();
        $form = $this->createForm('AdministrationBundle\Form\NoteType', $note);
        $form->handleRequest($request);
        $MoyenneGeneral = 0;
        if ($form->isSubmitted() && $form->isValid() && (isset($_POST['note']))) {
            $em = $this->getDoctrine()->getManager();
            $resultat = $_POST['note'];
            $somme = 0;
            $count = 0;
            foreach ($resultat as $valeur) {
                if (!empty($valeur)) {
                    $somme += $valeur; // Ou $somme = $somme + $valeur;
                    $count += 1;
                }


            }

            $em->persist($note);
            $em->flush();

            return $this->redirectToRoute('listN', array('id' => $note->getId()));
        }


        return $this->render('@Administration/Note/calcul.html.twig', array(
            'note' => $note,
            'form' => $form->createView(),
        ));

    }

    public function ExporterAction()
    {
        $em = $this->getDoctrine()->getManager();
        $notes = $em->getRepository('AdministrationBundle:Note')->findAll();
        $writer = $this->container->get('egyg33k.csv.writer');
        $csv = $writer::createFromFileObject(new \SplTempFileObject());
        $csv->insertOne(['cc', 'test', 'examen']);
        foreach ($notes as $note) {
            $csv->insertOne(['$note->getCc()', '$note->getTest()', '$note->getExamen()']);

        }
        $csv->output('notes.csv');
        die('exporter');
    }





        public function updateAction()
    {
        return $this->render('AdministrationBundle:Note:update.html.twig', array(
            // ...
        ));
    }
    public function ajoutNAction(Request $request)
    { $note = new Note();
        $form = $this->createForm('AdministrationBundle\Form\NoteType', $note);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($note);
            $em->flush();

            return $this->redirectToRoute('listN', array('id' => $note->getId()));
        }


        return $this->render('@Administration/Note/ajoutN.html.twig', array(
            'note' => $note,
            'form' => $form->createView(),
        ));
    }}


