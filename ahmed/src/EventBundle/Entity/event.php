<?php
namespace EventBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * event
 *
 * @ORM\Table(name="event")
 * @ORM\Entity(repositoryClass="EventBundle\Repository\EventRepository")
 */
class event
{
    /**
     * @var int
     *
     * @ORM\Column(name="idevent", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $idevent;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=255)
     */
    private $titre;


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut_event", type="datetime")
     *
     * @Assert\GreaterThan("today",message="Veuiller saisir une date supérieure à celle d'aujourd'hui")
     */
    private $dateDebutEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_f_event", type="datetime")
     *
     * @Assert\GreaterThan(propertyPath="dateDebutEvent",message="Veuiller saisir une date supérieure à celle de la date début")
     */
    private $dateFEvent;
    /**
     *
     * @ORM\Column(type="integer")
     */
    private $NbP  ;

    /**
     * @return mixed
     */
    public function getNbP()
    {
        return $this->NbP;
    }

    /**
     * @param mixed $NbP
     */
    public function setNbP($NbP)
    {
        $this->NbP = $NbP;
    }


    /**
     * @return \DateTime
     */
    public function getDateDebutEvent()
    {
        return $this->dateDebutEvent;
    }

    /**
     * @param \DateTime $dateDebutEvent
     *
     */
    public function setDateDebutEvent($dateDebutEvent)
    {
        $this->dateDebutEvent = $dateDebutEvent;
    }

    /**
     * @return \DateTime
     */
    public function getDateFEvent()
    {
        return $this->dateFEvent;
    }

    /**
     * @param \DateTime $dateFEvent
     */
    public function setDateFEvent($dateFEvent)
    {
        $this->dateFEvent = $dateFEvent;
    }

    /**
     * @return string
     */
    public function getAdresseEvent()
    {
        return $this->adresseEvent;
    }

    /**
     * @param string $adresseEvent
     */
    public function setAdresseEvent($adresseEvent)
    {
        $this->adresseEvent = $adresseEvent;
    }

    /**
     * @return string
     */
    public function getTypeEvent()
    {
        return $this->typeEvent;
    }

    /**
     * @param string $typeEvent
     */
    public function setTypeEvent($typeEvent)
    {
        $this->typeEvent = $typeEvent;
    }

    /**
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="adresse_event", type="string", length=255)
     */

    private $adresseEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="type_event", type="string", length=255)
     */
    private $typeEvent;

    /**
     * @return int
     */
    public function getIdevent()
    {
        return $this->idevent;
    }

    /**
     * @param int $idevent
     */
    public function setIdevent($idevent)
    {
        $this->idevent = $idevent;
    }








    /**
     * Set titre
     *
     * @param string $titre
     *
     * @return event
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;

        return $this;
    }

    /**
     * Get titre
     *
     * @return string
     */
    public function getTitre()
    {
        return $this->titre;
    }

    public function getUploadDir()
    {
        return 'uploads/images/Event';
    }
    public  function upload()
    {
        $this->image->move($this->getUploadDir(),$this->image->getClientOriginalName());
        $this->image=$this->image->getClientOriginalName();
    }



    /**
     * @var string
     *
     * @ORM\Column(name="image", type="string", length=255)
     * @Assert\NotBlank(message="Ajouter une image jpg")
     * @Assert\File(mimeTypes={ "image/jpeg" })
     */
    private $image;


    /**
     * Set image
     *
     * @param string $image
     *
     * @return event
     */
    public function setImage($image)
    {
        $this->image = $image;

        return $this;
    }

    /**
     * Get image
     *
     * @return string
     */
    public function getImage()
    {
        return $this->image;
    }

    /**
     * Set dateDebut
     *
     * @param \DateTime $dateDebut
     *
     * @return event
     */
    public function setDateD($dateDebut)
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    /**
     * Get dateDebut
     *
     * @return \DateTime
     */
    public function getDateDebut()
    {
        return $this->dateDebutEvent;
    }

    /**
     * Set dateFin
     *
     * @param \DateTime $dateFin
     *
     * @return event
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    /**
     * Get dateFin
     *
     * @return \DateTime
     */
    public function getDateFin()
    {
        return $this->dateFEvent;
    }

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description ;

}

