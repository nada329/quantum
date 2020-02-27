<?php

namespace activiteBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\OneToMany;
/**
 * Questions
 *
 * @ORM\Table(name="questions")
 * @ORM\Entity(repositoryClass="activiteBundle\Repository\QuestionsRepository")
 */
class Questions
{



    /**
     * Constructor
     */
    public function __construct()
    {
        $this->reponses = new \Doctrine\Common\Collections\ArrayCollection();
    }


    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="question", type="string", length=255)
     */
    private $question;




    /**
     * @var Array<activiteBundle\Entity\ReponseEleve>
     *
     * @ORM\OneToMany(targetEntity="activiteBundle\Entity\Reponse", mappedBy="question", cascade={"persist", "remove"}, orphanRemoval=true)
     */
    private $reponses ;



    /**
     * Add reponse
     *
     * @param Reponse $reponse
     *
     * @return Reponse
     */
    public function addReponse(Reponse $reponse)
    {
        $this->reponses[] = $reponse;

        return $this;
    }

    /**
     * Remove reponse
     *
     * @param Reponse $reponse
     */
    public function removeReponse(Reponse $reponse)
    {
        $this->reponses->removeElement($reponse);
    }

    /**
     * Get reponses
     *
     * @return Collection
     */
    public function getReponses()
    {
        return $this->reponses;
    }


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
     * Set question
     *
     * @param string $question
     *
     * @return Questions
     */
    public function setQuestion($question)
    {
        $this->question = $question;

        return $this;
    }

    /**
     * Get question
     *
     * @return string
     */
    public function getQuestion()
    {
        return $this->question;
    }


    public function __toString()
    {
        return (string) $this->question;
    }
}

