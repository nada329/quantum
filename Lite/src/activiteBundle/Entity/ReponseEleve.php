<?php

namespace activiteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use ScolariteBundle\Entity\Eleve ;

/**
 * ReponseEleve
 *
 * @ORM\Table(name="reponse_eleve")
 * @ORM\Entity(repositoryClass="activiteBundle\Repository\ReponseEleveRepository")
 */
class ReponseEleve
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
     * @var int
     *
     * @ORM\Column(name="score", type="integer")
     */
    private $score;




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
     * Set score
     *
     * @param integer $score
     *
     * @return ReponseEleve
     */
    public function setScore($score)
    {
        $this->score = $score;

        return $this;
    }


    /**
     * @ORM\ManyToOne(targetEntity="Reponse")
     * @ORM\JoinColumn(name="reponse_id",referencedColumnName="id")
     */
    private $reponse;
    /**
     * @ORM\ManyToOne(targetEntity="Questions")
     * @ORM\JoinColumn(name="questions_id",referencedColumnName="id")
     */
    private $question;




    /**
     * @ORM\ManyToOne(targetEntity="ScolariteBundle\Entity\Eleve")
     * @ORM\JoinColumn(name="eleve_id",referencedColumnName="id")
     */
    private $eleve;

    /**
     * Get score
     *
     * @return int
     */
    public function getScore()
    {
        return $this->score;
    }

    /**
     * Set eleve
     *
     * @param \ScolariteBundle\Entity\Eleve $eleve
     *
     * @return ReponseEleve
     */
    public function setEleve(\ScolariteBundle\Entity\Eleve $eleve = null)
    {
        $this->eleve = $eleve;

        return $this;
    }

    /**
     * Get eleve
     *
     * @return \ScolariteBundle\Entity\Eleve
     */
    public function getEleve()
    {
        return $this->eleve;
    }

    /**
     * Set reponse
     *
     * @param \activiteBundle\Entity\Reponse $reponse
     *
     * @return ReponseEleve
     */
    public function setReponse(\activiteBundle\Entity\Reponse $reponse = null)
    {
        $this->reponse = $reponse;

        return $this;
    }

    /**
     * Get reponse
     *
     * @return \activiteBundle\Entity\Reponse
     */
    public function getReponse()
    {
        return $this->reponse;
    }

    /**
     * Set question
     *
     * @param \activiteBundle\Entity\Questions $question
     *
     * @return ReponseEleve
     */
    public function setQuestion(\activiteBundle\Entity\Questions $question = null)
    {
        $this->question = $question;

        return $this;
    }

    /**
     * Get question
     *
     * @return \activiteBundle\Entity\Questions
     */
    public function getQuestion()
    {
        return $this->question;
    }

    public function __toString()
    {
        return (string) $this->getQuestion() + $this->getQuestion();
    }
}
