<?php

namespace AdministrationBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Note
 *
 * @ORM\Table(name="note")
 * @ORM\Entity(repositoryClass="AdministrationBundle\Repository\NoteRepository")
 */
class Note
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var float
     *
     * @ORM\Column(name="cc", type="float")
     */
    private $cc;

    /**
     * @var float
     *
     * @ORM\Column(name="test", type="float")
     */
    private $test;

    /**
     * @return float
     */
    public function getMoyenneGeneral()
    {
        return $this->MoyenneGeneral;
    }

    /**
     * @param float $MoyenneGeneral
     */
    public function setMoyenneGeneral($MoyenneGeneral)
    {
        $this->MoyenneGeneral = $MoyenneGeneral;
    }

    /**
     * @var float
     *
     * @ORM\Column(name="examen", type="float")
     */
    private $examen;
    /**
     * @var float
     *
     * @ORM\Column(name="MoyenneGeneral", type="float")
     */
    private $MoyenneGeneral;
    /**
     * @ORM\ManyToOne(targetEntity="Matiere")
     * @ORM\JoinColumn(name="IdMatiere",referencedColumnName="id")
     */

private $Matiere;

    /**
     * @ORM\ManyToOne(targetEntity="Eleve")
     * @ORM\JoinColumn(name="IdEleve",referencedColumnName="id")

     */
private $Eleve ;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set cc
     *
     * @param float $cc
     *
     * @return Note
     */
    public function setCc($cc)
    {
        $this->cc = $cc;

        return $this;
    }

    /**
     * Get cc
     *
     * @return float
     */
    public function getCc()
    {
        return $this->cc;
    }

    /**
     * Set test
     *
     * @param float $test
     *
     * @return Note
     */
    public function setTest($test)
    {
        $this->test = $test;

        return $this;
    }

    /**
     * Get test
     *
     * @return float
     */
    public function getTest()
    {
        return $this->test;
    }

    /**
     * Set examen
     *
     * @param float $examen
     *
     * @return Note
     */
    public function setExamen($examen)
    {
        $this->examen = $examen;

        return $this;
    }

    /**
     * Get examen
     *
     * @return float
     */
    public function getExamen()
    {
        return $this->examen;
    }



    /**
     * @return Note
     */
    public function getMatiere()
    {
        return $this->Matiere;
    }

    /**
     * @param Note $Matiere
     */
    public function setMatiere($Matiere)
    {
        $this->Matiere = $Matiere;
    }

    /**
     * @return Note
     */
    public function getEleve()
    {
        return $this->Eleve;
    }

    /**
     * @param Note $Eleve
     */
    public function setEleve($Eleve)
    {
        $this->Eleve = $Eleve;
    }
}

